package org.wcs.lemursportal.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wcs.lemursportal.model.post.Metadata;
import org.wcs.lemursportal.model.post.VueTaxonomiIdMetadata;
import org.wcs.lemursportal.model.post.VueTopicIdMetadata;
import org.wcs.lemursportal.repository.post.VueTaxonomiIdMetadataCrudRepository;
import org.wcs.lemursportal.repository.post.VueTopicIdMetadataCrudRepository;
import org.wcs.lemursportal.service.post.MetadataService;

/**
 * @author Mikajy <mikajy401@gmail.com>
 *
 */
@RestController
@Transactional
public class RestUtilController extends BaseController {

    @Autowired
    ServletContext context;
    @Autowired
    VueTaxonomiIdMetadataCrudRepository vueTaxonomiIdMetadataCrudRepository;
    @Autowired
    VueTopicIdMetadataCrudRepository vueTopicIdMetadataCrudRepository;
    @Autowired
    MetadataService metadataService;

    @GetMapping("/metadata/{id}/taxonomis")
    public List<VueTaxonomiIdMetadata> getAllVueTaxonomiIdMetadataCrudRepository(@PathVariable("id") Integer idMetadata) {
        VueTaxonomiIdMetadata forSearch = new VueTaxonomiIdMetadata();
        forSearch.setIdMetadata(idMetadata);
        Example<VueTaxonomiIdMetadata> example = Example.of(forSearch);
        return vueTaxonomiIdMetadataCrudRepository.findAll(example);
    }

    @GetMapping("/metadata/{id}/topics")
    public List<VueTopicIdMetadata> getAllVueTopicIdMetadataCrudRepository(@PathVariable("id") Integer idMetadata) {
        VueTopicIdMetadata forSearch = new VueTopicIdMetadata();
        forSearch.setIdMetadata(idMetadata);
        Example<VueTopicIdMetadata> example = Example.of(forSearch);
        return vueTopicIdMetadataCrudRepository.findAll(example);
    }

    @GetMapping("/metadata/{id}/all")
    public HashMap<String, Object> getMetadatasAll(@PathVariable("id") Integer idMetadata) {
        //Get metadata
        Metadata metadata = new Metadata();
        metadata.setId(idMetadata);
        metadata = metadataService.findById(metadata.getId());
        metadata.setPhotos(null);
        //Get all topic
        VueTopicIdMetadata forSearch = new VueTopicIdMetadata();
        forSearch.setIdMetadata(idMetadata);
        Example<VueTopicIdMetadata> example = Example.of(forSearch);
        List<VueTopicIdMetadata> listeVTM = vueTopicIdMetadataCrudRepository.findAll(example);
        //Get all taxonomi
        VueTaxonomiIdMetadata forSearch2 = new VueTaxonomiIdMetadata();
        forSearch2.setIdMetadata(idMetadata);
        Example<VueTaxonomiIdMetadata> example1 = Example.of(forSearch2);
        List<VueTaxonomiIdMetadata> listeVTaxM = vueTaxonomiIdMetadataCrudRepository.findAll(example1);
        HashMap<String, Object> valiny = new HashMap<>();
        valiny.put("metadata", metadata);
        valiny.put("topics", listeVTM);
        valiny.put("taxonomi", listeVTaxM);
        return valiny;
    }

    //Getter and setter
    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public VueTaxonomiIdMetadataCrudRepository getVueTaxonomiIdMetadataCrudRepository() {
        return vueTaxonomiIdMetadataCrudRepository;
    }

    public void setVueTaxonomiIdMetadataCrudRepository(VueTaxonomiIdMetadataCrudRepository vueTaxonomiIdMetadataCrudRepository) {
        this.vueTaxonomiIdMetadataCrudRepository = vueTaxonomiIdMetadataCrudRepository;
    }

    public VueTopicIdMetadataCrudRepository getVueTopicIdMetadataCrudRepository() {
        return vueTopicIdMetadataCrudRepository;
    }

    public void setVueTopicIdMetadataCrudRepository(VueTopicIdMetadataCrudRepository vueTopicIdMetadataCrudRepository) {
        this.vueTopicIdMetadataCrudRepository = vueTopicIdMetadataCrudRepository;
    }

    public MetadataService getMetadataService() {
        return metadataService;
    }

    public void setMetadataService(MetadataService metadataService) {
        this.metadataService = metadataService;
    }
}
