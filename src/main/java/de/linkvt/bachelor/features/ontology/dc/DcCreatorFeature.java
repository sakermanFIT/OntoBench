package de.linkvt.bachelor.features.ontology.dc;

import de.linkvt.bachelor.features.Feature;
import de.linkvt.bachelor.features.FeatureCategory;
import de.linkvt.bachelor.features.ontology.OntologyConstants;

import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.vocab.Namespaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DcCreatorFeature extends Feature {

  @Autowired
  OntologyConstants ontologyConstants;

  @Override
  public void addToOntology() {
    OWLAnnotationProperty annotationProperty = factory.getOWLAnnotationProperty(IRI.create(Namespaces.DC + "creator"));
    OWLAnnotation dcCreator = factory.getOWLAnnotation(annotationProperty, factory.getOWLLiteral(ontologyConstants.getCreator()));

    addChangeToOntology(new AddOntologyAnnotation(ontology, dcCreator));
  }

  @Override
  public String getName() {
    return "dc:creator";
  }

  @Override
  public String getToken() {
    return "dccreator";
  }

  @Override
  public FeatureCategory getCategory() {
    return FeatureCategory.ONTOLOGY;
  }
}