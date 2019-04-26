package nosejob;

public class DummyClass {

    private int age;
    private String name;

    public DummyClass(int ageInput, String nameInput) {
        this.age = ageInput;
        this.name = nameInput;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public int birthday(int personAge) {
        return personAge++;
    }

    public void voidMethod(int personAge) {
        int z = personAge+20;
    }


    private String greeting() {
        return "hi";
    }

    protected String protectedMethod(String greeting) {
         if (greeting.equals("hi")) {
             return greeting;
         } else {
             return "goodbye";
         }
    }

}
