package cxq.aimatoffer.question2.singleton;

public class InnerClassSingleton {
    
    private static class SingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
        
    }
    
    private String sentence = "init";
    
    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
    
    public void say() {
        System.out.println(sentence);
    }
    
    private InnerClassSingleton() {}
    
    // for test
    public static void main(String[] args) {
        InnerClassSingleton ins = InnerClassSingleton.getInstance();
        ins.say();
        ins.setSentence("hello, world");
        InnerClassSingleton ins2 = InnerClassSingleton.getInstance();
        ins2.say();
    }

}
