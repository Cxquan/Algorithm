package cxq.aimatoffer.question2.singleton;

public enum EnumSingleton {
    instance;
    private String sentence = "init";
    
    public void say() {
        System.out.println(sentence);
    } 
    
    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    // for test
    public static void main(String[] args) {
        EnumSingleton.instance.say();
        EnumSingleton.instance.setSentence("hello, world");
        EnumSingleton.instance.say();
    }

}
