import java.util.*;

public class Main {
    static class BrokenStudent {
        final String regNo, name;
        BrokenStudent(String r,String n){regNo=r;name=n;}
        @Override public boolean equals(Object o){return o instanceof BrokenStudent && regNo.equals(((BrokenStudent)o).regNo);}
        // Deliberately inherits Object.hashCode(): violates equals/hashCode contract.
    }
    static class Student {
        final String regNo, name;
        Student(String r,String n){regNo=r;name=n;}
        @Override public boolean equals(Object o){return o instanceof Student && regNo.equals(((Student)o).regNo);}
        @Override public int hashCode(){return Objects.hash(regNo);}
        @Override public String toString(){return regNo+":"+name;}
    }
    static class MutableStudent {
        String regNo;
        MutableStudent(String r){regNo=r;}
        @Override public boolean equals(Object o){return o instanceof MutableStudent && regNo.equals(((MutableStudent)o).regNo);}
        @Override public int hashCode(){return Objects.hash(regNo);}
    }
    public static void main(String[] args) {
        System.out.println("Q3 - equals/hashCode contract");
        Set<BrokenStudent> broken = new HashSet<>();
        broken.add(new BrokenStudent("S101","A")); broken.add(new BrokenStudent("S101","A copy"));
        System.out.println("Broken class set size (hash values are identity-based; result can vary with collisions): " + broken.size());
        Set<Student> fixed = new HashSet<>();
        fixed.add(new Student("S101","A")); fixed.add(new Student("S101","A copy"));
        fixed.add(new Student("S102","B"));
        System.out.println("Unique students: " + fixed.size());
        Set<MutableStudent> mutable = new HashSet<>();
        MutableStudent m = new MutableStudent("S201"); mutable.add(m); m.regNo="S202";
        System.out.println("After changing hash key, contains same object: " + mutable.contains(m) + " (may be false)");
        System.out.println("Use immutable fields in equals/hashCode while an object is stored in a hash collection.");
    }
}