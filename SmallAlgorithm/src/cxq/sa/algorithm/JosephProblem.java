package cxq.sa.algorithm;

public class JosephProblem {
    // 法一
    public void countN(int n, int m) {
        int num = 1;
        for (int i = 2; i <=n; i++) {
            num = (num + m) % n;
            if (num==0) num = i;
        }
            System.out.print(" "+num);
    }
    
    // 法二
    static class Person {
        int code;
        Person next;
        public Person(int i) {
            code = i;
        }
    }
    public void countN2(int n, int m) {
        Person a1 = new Person(1); 
        Person tmpPerson = a1;
        for (int i = 2; i <= n; i++) {
            tmpPerson.next = new Person(i);
            tmpPerson = tmpPerson.next;
        }
        tmpPerson.next = a1;

        while(tmpPerson.next != tmpPerson) {
            for(int i=1; i<m; i++){
                tmpPerson = tmpPerson.next;
            }
            System.out.print(tmpPerson.next.code+" ");
            tmpPerson.next = tmpPerson.next.next;
        }
        System.out.print(tmpPerson.code);
    }
}
