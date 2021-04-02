package it.polimi.ingsw.core;

public class SpecialExtraResource implements SpecialAbility{
    private Resource resource;

    public SpecialExtraResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String getDescription() {
        return "Quando ricevi risorse al mercato, ogni biglia bianca presente nella riga/colonna ti fornisce la risorsa indicata. Se giochi due Leader con questa abilità, " +
                "quando prendi risorse al mercato, devi scegliere quale risorsa (tra quelle fornite dai leader) prendere per ciascuna biglia bianca " +
                "(non puoi quindi prendere entrambe le risorse da una singola biglia bianca).";
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public void evokeEffect() {

    }
}