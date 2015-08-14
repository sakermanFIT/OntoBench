package de.linkvt.bachelor.features.classexpressions.restrictions.object.cardinality;

import de.linkvt.bachelor.features.Feature;
import de.linkvt.bachelor.features.FeatureCategory;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.springframework.stereotype.Component;

@Component
public class OwlObjectMaxQualifiedCardinalityFeature extends Feature {
  @Override
  public void addToOntology() {
    OWLObjectProperty hasRoommates = featurePool.getExclusiveProperty(":hasRoommates");
    OWLClass range = featurePool.getExclusiveClass(":MaxQualifiedCardinalityRange");
    addToGenericDomainAndNewRange(hasRoommates, range);

    OWLClass student = featurePool.getExclusiveClass(":Student");
    OWLObjectMaxCardinality maxCardinality = factory.getOWLObjectMaxCardinality(3, hasRoommates, student);
    addAxiomToOntology(factory.getOWLSubClassOfAxiom(range, maxCardinality));
  }

  @Override
  public String getName() {
    return "owl:maxQualifiedCardinality (Object Property)";
  }

  @Override
  public String getToken() {
    return "maxqualifiedcardinality";
  }

  @Override
  public FeatureCategory getCategory() {
    return FeatureCategory.OBJECT_PROPERTY_RESTRICTIONS;
  }
}
