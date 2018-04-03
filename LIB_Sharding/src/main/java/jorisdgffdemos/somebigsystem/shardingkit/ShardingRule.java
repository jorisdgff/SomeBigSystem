package jorisdgffdemos.somebigsystem.shardingkit;

import jorisdgffdemos.somebigsystem.domain.DomainEnums;

public class ShardingRule {

    public enum RuleUsage{

        USEASKEY, USEITSKEY;
    }

    public final DomainEnums.Type key;
    public final RuleUsage usage;

    public ShardingRule(DomainEnums.Type key, RuleUsage usage){

        this.key = key;
        this.usage = usage;
    }
}
