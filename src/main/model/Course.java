package model;

public class Course {

    public String code;

    public Course(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Course course = (Course) o;

        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
