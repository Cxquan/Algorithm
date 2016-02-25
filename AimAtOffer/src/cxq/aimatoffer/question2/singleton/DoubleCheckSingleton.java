package cxq.aimatoffer.question2.singleton;


public class DoubleCheckSingleton {
    private DoubleCheckSingleton() {}
    
    private static DoubleCheckSingleton instance = null;
    
    private String sentence = "init";
    
    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public static final DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized(DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
    
    public void say() {
        System.out.println(sentence);
    }

    // for test
    public static void main(String[] args) {
        DoubleCheckSingleton dcs = DoubleCheckSingleton.getInstance();
        dcs.setSentence("hello, world");
        dcs.say();
        DoubleCheckSingleton dcs2 = DoubleCheckSingleton.getInstance();
        dcs2.say();
    }

}
