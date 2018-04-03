package jorisdgffdemos.somebigsystem.shardingkit;

import jorisdgffdemos.somebigsystem.domain.DomainEnums;

import java.util.HashMap;

public class ShardingRules {

    private HashMap<DomainEnums.Type, ShardingRule> rules;
    private static ShardingRules instance;

    private ShardingRules(){

        //TODO Read from configfile
        rules = new HashMap<>();
        rules.put(DomainEnums.Type.BUYER, new ShardingRule(DomainEnums.Type.HOLDING, ShardingRule.RuleUsage.USEASKEY));
        rules.put(DomainEnums.Type.VENDOR, new ShardingRule(DomainEnums.Type.BUYER, ShardingRule.RuleUsage.USEITSKEY));
        rules.put(DomainEnums.Type.ARTICLE,  new ShardingRule(DomainEnums.Type.VENDOR,ShardingRule.RuleUsage.USEASKEY ));
        rules.put(DomainEnums.Type.PURCHASEORDER, new ShardingRule(DomainEnums.Type.ARTICLE, ShardingRule.RuleUsage.USEITSKEY));

        rules.put(DomainEnums.Type.REPRESENTIVE, new ShardingRule(DomainEnums.Type.HOLDING, ShardingRule.RuleUsage.USEASKEY));
        rules.put(DomainEnums.Type.CUSTOMER, new ShardingRule(DomainEnums.Type.REPRESENTIVE, ShardingRule.RuleUsage.USEASKEY));
        rules.put(DomainEnums.Type.ARTICLE,  new ShardingRule(DomainEnums.Type.CUSTOMER,ShardingRule.RuleUsage.USEITSKEY));
    }

    public ShardingRule getRule(DomainEnums.Type type){

        return rules.get(type);
    }

    public static ShardingRules getInstance(){

        if(instance == null){

            instance = new ShardingRules();
        }

        return instance;
    }
}