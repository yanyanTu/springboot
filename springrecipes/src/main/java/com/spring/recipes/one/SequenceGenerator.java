package com.spring.recipes.one;

import com.spring.recipes.one.service.Generator;
import com.spring.recipes.one.service.IPrefixGenerator;
import com.spring.recipes.one.service.Mandatory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SequenceGenerator {
    @Autowired
    @Generator("prefix")
    private IPrefixGenerator prefixGenerator ;
   private String prefix ;
   private String suffix ;
   private int initial ;
   private int counter ;

   private List<Object> suffixes ;

   private Object[] suffixObjs ;

   private Map<Object, Object> suffixMap ;

    public void setPrefixGenerator(IPrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public synchronized String getPrefixGenerator(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefixGenerator.getPrefix());
        buffer.append(initial + counter++);
        buffer.append(suffix);

        return buffer.toString();
    }

    public void setSuffixMap(Map<Object, Object> suffixMap) {
        this.suffixMap = suffixMap;
    }

    public void setSuffixObjs(Object[] suffixObjs) {
        this.suffixObjs = suffixObjs;
    }

    public void setSuffixes(List<Object> suffixes) {
        this.suffixes = suffixes;
    }

    public SequenceGenerator() {
    }

    public SequenceGenerator(String prefix, String suffix, int initial) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.initial = initial;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Mandatory
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public synchronized String getSequence(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);
        buffer.append(initial + counter++);
        buffer.append(suffix);

        return buffer.toString();
    }

    public synchronized String getList(){
        StringBuffer buffer = new StringBuffer();
        for(Object tmpSuffix : suffixes){
            buffer.append("-");
            buffer.append(tmpSuffix);
        }
        return buffer.toString();
    }

    public synchronized String getMapValue(){
        StringBuffer buffer = new StringBuffer();
        for(Map.Entry entry : suffixMap.entrySet()){
            buffer.append("-");
            buffer.append(entry.getKey());
            buffer.append("@");
            buffer.append(entry.getValue());
        }
        return buffer.toString();
    }

}
