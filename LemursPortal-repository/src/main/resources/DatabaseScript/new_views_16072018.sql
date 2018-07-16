/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  WCS
 * Created: 16 juil. 2018
 */

CREATE OR REPLACE VIEW public.vue_topic_id_metadata AS
 SELECT t.id,
    t.date_creation,
    t.description,
    t.date_modif,
    t.libelle,
    t.created_by,
    t.modified_by,
    amt.id_metadata
   FROM association_metadata_topic amt
     JOIN thematique t ON amt.id_topic = t.id
  WHERE t.deleted = false;

CREATE OR REPLACE VIEW public.vue_taxonomi_id_metadata AS
 SELECT t.idtaxonomibase,
    t.higherclassification,
    t.kingdom,
    t.phylum,
    t.dwcclass,
    t.dwcorder,
    t.dwcfamily,
    t.genus,
    t.genussource,
    t.specificepithet,
    t.specificepithetsource,
    t.infraspecificepithet,
    t.infraspecificepithetsource,
    t.scientificname,
    t.scientificnameauthorship,
    t.acceptednameusage,
    t.basisofrecord,
    t.frenchvernacularname,
    t.malagasyvernacularname,
    t.englishvernacularname,
    t.habitat_fr,
    t.habitat_en,
    t.habitatsource,
    t.ecology_fr,
    t.ecology_en,
    t.ecologysource,
    t.behavior_fr,
    t.behavior_en,
    t.behaviorsource,
    t.threat_fr,
    t.threat_en,
    t.threatsource,
    t.morphology_fr,
    t.morphology_en,
    t.protectedareaoccurrences,
    t.germany_vernacular_name,
    t.length_and_weight,
    t.color_en,
    t.color_fr,
    t.color_source,
    t.population_density,
    t.population_density_source,
    t.conservation_status,
    t.iucn_source,
    t.protected_area_occurrences_sources,
    t.species_expert,
    t.new_and_updates,
    t.top_five_publication,
    t.length_and_weight_source,
    amt.id_metadata
   FROM association_metadata_taxonomi amt
     JOIN taxonomi_base t ON amt.id_taxonomi = t.idtaxonomibase;

CREATE OR REPLACE VIEW public.vue_metadata_id_publication AS
 SELECT meta.id,
    meta.url,
    meta.coverage,
    meta.description,
    meta.language,
    meta.relation,
    meta.source,
    meta.subject,
    meta.title,
    meta.format,
    meta.file_format,
    meta.identifier,
    meta.type,
    meta.contributor,
    meta.creator,
    meta.publisher,
    meta.rights,
    meta.id_document,
    meta.date_publication,
    meta.year,
    meta.bibliographic_resource,
    meta.id_utilisateur,
    meta.id_thematique,
    m.id AS id_publication
   FROM document d
     LEFT JOIN message m ON d.id = m.document_id
     LEFT JOIN metadata meta ON meta.id_document = d.id;