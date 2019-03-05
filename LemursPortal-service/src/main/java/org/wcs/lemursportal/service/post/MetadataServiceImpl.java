package org.wcs.lemursportal.service.post;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wcs.lemursportal.model.association.AssociationMetadataTaxonomi;
import org.wcs.lemursportal.model.association.AssociationMetadataTopic;
import org.wcs.lemursportal.model.association.BaseAssociation;
import org.wcs.lemursportal.model.post.Document;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.user.UserView;
import org.wcs.lemursportal.repository.post.BaseAssociationCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataCrudRepository;
import org.wcs.lemursportal.repository.post.MetadataRepository;
import org.wcs.lemursportal.repository.post.ThematiqueCrudRepository;
import org.wcs.lemursportal.repository.post.VueTaxonomiIdMetadataCrudRepository;
import org.wcs.lemursportal.repository.user.UserViewRepository;
import org.wcs.lemursportal.service.common.GenericCRUDServiceImpl;

@Service
@Transactional(readOnly = true)
public class MetadataServiceImpl extends GenericCRUDServiceImpl<Metadata, Integer> implements MetadataService {

    @Autowired
    MetadataCrudRepository metadataCrudRepository;
    @Autowired
    MetadataRepository metadataRepository;
    @Autowired
    DocumentService documentService;
    @Autowired
    BaseAssociationCrudRepository baseAssociationCrudRepository;
    @Autowired
    UserViewRepository userViewRepository;
    @Autowired
    ThematiqueCrudRepository thematiqueCrudRepository;
    @Autowired
    VueTaxonomiIdMetadataCrudRepository vueTaxonomiIdMetadataCrudRepository;

    @Override
    protected JpaRepository<Metadata, Integer> getJpaRepository() {
        return metadataCrudRepository;
    }

    @Override
    public List<String> findOneElementOfMetadata(Metadata metadata) {
        return metadataRepository.findOneElementOfMetadata(metadata);
    }

    /**
     *
     * @param metadata
     * @throws Exception
     */
    @Override
    @Transactional
    public void deleteMetadata(Metadata metadata) throws Exception {
        try {
            System.out.println("Delete metadata");
            List<BaseAssociation> liste = new ArrayList<>();
            try {
                AssociationMetadataTaxonomi a = new AssociationMetadataTaxonomi();
                a.setId1(metadata.getId());
                Example<AssociationMetadataTaxonomi> example = Example.of(a);
                liste.addAll(baseAssociationCrudRepository.findAll(example));
                System.out.println("All association taxonomi got");
            } catch (NullPointerException npe) {
            }
            try {
                AssociationMetadataTopic a = new AssociationMetadataTopic();
                a.setId1(metadata.getId());
                Example<AssociationMetadataTopic> example = Example.of(a);
                liste.addAll(baseAssociationCrudRepository.findAll(example));
                System.out.println("All association topics got");
            } catch (NullPointerException npe) {
            }
            if (liste.size() > 0) {
                for (BaseAssociation b : liste) {
                    baseAssociationCrudRepository.delete(b);
                }
                System.out.println("All Association deleted");
            }
            metadata = metadataCrudRepository.findOne(metadata.getId());
            Document d = null;
            if (metadata.getIdDocument() != null) {
                d = new Document();
                d.setFilename(documentService.findById(metadata.getIdDocument()).getFilename());
            }
            List<UserView> allUV = userViewRepository.findAll();
            for (UserView uv : allUV) {
                uv.setNbrDocument(uv.getNbrDocument() - 1);
                userViewRepository.save(uv);
            }
            metadataRepository.delete(metadata);
            System.out.println("document and metadata deleted");
            if (d != null) {
                documentService.deleteDocument(d);
            }
            System.out.println("File deleted");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void writeMetadataCsv(List<Metadata> metadata, List<String> listeColonnes,
            char separator, OutputStream output) throws Exception {
        output.write(239);
        output.write(187);
        output.write(191);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output,
                "UTF-8"));
        String header = "";

        for (String f : listeColonnes) {
            header += f.substring(0, 1).toUpperCase() + f.substring(1) + separator;
        }
        header = header.substring(0, header.length() - 1);
        writer.append(header);
        writer.newLine();

        for (Metadata row : metadata) {
            String field = "";
            for (int i = 0; i < listeColonnes.size(); i++) {
                String temp = "";
                switch (i) {
                    case 0:
                        temp = row.getTitle().replace("\"", "'");
                        break;
                    case 1:
                        temp = row.getLanguage().replace("\"", "'");
                        break;
                    case 2:
                        temp = row.getSubject().replace("\"", "'");
                        //                    make a list of species
                        AssociationMetadataTaxonomi amt = new AssociationMetadataTaxonomi();
                        amt.setId1(row.getId());
                        List<AssociationMetadataTaxonomi> listSpecies
                                = baseAssociationCrudRepository.findAll(Example.of(amt));
                        for (AssociationMetadataTaxonomi pasAmt : listSpecies) {
                            temp += "|" + vueTaxonomiIdMetadataCrudRepository.findOne(
                                    pasAmt.getId2()).getScientificname();
                        }
                        break;
                    case 3:
                        temp = row.getCoverage().replace("\"", "'");
                        break;
//                  make a list of topics
                    case 4:
                        AssociationMetadataTopic amtopic = new AssociationMetadataTopic();
                        amtopic.setId1(row.getId());
                        List<AssociationMetadataTopic> listTopics
                                = baseAssociationCrudRepository.findAll(Example.of(amtopic));
                        for (AssociationMetadataTopic pasAmt : listTopics) {
                            temp += thematiqueCrudRepository.findOne(pasAmt.getId2())
                                    .getLibelle() + "|";
                        }
                        if (!temp.isEmpty()) {
                            temp = temp.substring(0, temp.length() - 1);
                        }
                        break;
                    case 5:
                        temp = row.getBibliographicResource().replace("\"", "'");
                        break;
                    case 6:
                        temp = row.getYear().replace("\"", "'");
                        break;
                    case 7:
                        temp = "GERP | lemursportal : https://www.lemursportal.org";
                        break;
                    case 8:
                        temp = row.getRights().replace("\"", "'");
                        break;
                    case 9:
                        temp = row.getCreator().replace("\"", "'");
                        break;
                    case 10:
                        temp = row.getTitle().replace("\"", "'");
                        break;
                    case 12:
                        temp = row.getDescription().replace("\"", "'");
                        break;
                    case 27:
                        temp = row.getUrl().replace("\"", "'");
                        break;
                    case 28:
                        temp = row.getTitle().replace("\"", "'");
                        break;
                    case 37:
                        temp = row.getPublisher().replace("\"", "'");
                        break;
                    case 38:
                        temp = row.getContributor().replace("\"", "'");
                        break;
                    case 11:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 39:
                        temp = "";
                        break;
                }

                if (temp.contains(String.valueOf(separator))) {
                    temp = "\"" + temp + "\"";
                }

                field += temp.replace("\n", "").replace("\r", "")
                        + String.valueOf(separator);
            }

            field = field.substring(0, field.length() - 1);
            writer.append(field);
            writer.newLine();
        }
        writer.flush();
    }

    @Override
    public List<Metadata> findAll(Metadata metadata) {
        return metadataCrudRepository.findAll(Example.of(metadata));
    }

    @Override
    public List<Metadata> findAll(Metadata metadata, Pageable pageable) {
        return metadataCrudRepository.findAll(Example.of(metadata), pageable).getContent();
    }
}
