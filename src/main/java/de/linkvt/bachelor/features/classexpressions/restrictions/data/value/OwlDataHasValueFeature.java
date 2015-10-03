package de.linkvt.bachelor.features.classexpressions.restrictions.data.value;

import de.linkvt.bachelor.features.Feature;
import de.linkvt.bachelor.features.FeatureCategory;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.springframework.stereotype.Component;

@Component
public class OwlDataHasValueFeature extends Feature {
  @Override
  public void addToOntology() {
    OWLDataProperty property = factory.getOWLDataProperty(":dataHasValueProperty", pm);

    OWLDataHasValue restriction = factory.getOWLDataHasValue(property, factory.getOWLLiteral(6));
    addToGenericDomainAndNewRange(property, OWL2Datatype.XSD_NON_NEGATIVE_INTEGER.getDatatype(factory));

    OWLClass hasValue = featurePool.getReusableClass(":DataHasValue");
    addAxiomToOntology(factory.getOWLSubClassOfAxiom(hasValue, restriction));
  }

  @Override
  public String getName() {
    return "owl:hasValue (Data Property)";
  }

  @Override
  public String getToken() {
    return "hasvaluedata";
  }

  @Override
  public FeatureCategory getCategory() {
    return FeatureCategory.DATA_PROPERTY_RESTRICTIONS;
  }
}
