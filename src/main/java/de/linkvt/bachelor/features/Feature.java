package de.linkvt.bachelor.features;

import de.linkvt.bachelor.generator.FeaturePool;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for ontology features.
 */
public abstract class Feature {

  @Autowired
  protected OWLDataFactory factory;

  @Autowired
  protected FeaturePool featurePool;

  @Autowired
  protected OWLOntology ontology;


  /**
   * Adds this feature to the passed ontology.
   */
  public abstract void addToOntology();

  protected void addAxiomToOntology(OWLAxiom axiom) {
    ontology.getOWLOntologyManager().addAxiom(ontology, axiom);
  }

  protected void addToGenericDomainAndNewRange(OWLObjectProperty property, OWLClassExpression range) {
    OWLClass domain = featurePool.getReusableClass();
    addProperty(domain, property, range);
  }

  protected void addToGenericDomainAndNewRange(OWLDataProperty property, OWLDataRange range) {
    OWLClass domain = featurePool.getReusableClass();
    addProperty(domain, property, range);
  }

  protected void addProperty(OWLClass domain, OWLObjectProperty property, OWLClassExpression range) {
    addAxiomToOntology(factory.getOWLObjectPropertyDomainAxiom(property, domain));
    addAxiomToOntology(factory.getOWLObjectPropertyRangeAxiom(property, range));
  }

  protected void addProperty(OWLClass domain, OWLDataProperty property, OWLDataRange range) {
    addAxiomToOntology(factory.getOWLDataPropertyDomainAxiom(property, domain));
    addAxiomToOntology(factory.getOWLDataPropertyRangeAxiom(property, range));
  }

  protected void addChangeToOntology(OWLOntologyChange change) {
    ontology.getOWLOntologyManager().applyChange(change);
  }

  /**
   * @return the name of this feature
   */
  public abstract String getName();

  /**
   * @return the unique token of this feature
   */
  public abstract String getToken();

  /**
   * @return the category of this feature
   */
  public abstract FeatureCategory getCategory();

  /**
   * @return true, if this feature is a base feature
   */
  public boolean isGeneral() {
    return false;
  }

  @Override
  public String toString() {
    return getName();
  }
}
