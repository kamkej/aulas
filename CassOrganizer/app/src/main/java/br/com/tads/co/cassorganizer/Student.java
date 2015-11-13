package br.com.tads.co.cassorganizer;

/**
 * Created by julio on 11/13/15.
 */
public class Student {
    private int id;
    private String name;
    private Float nota;
    private String status;
    private String turma;

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nota=" + nota +
                ", status='" + status + '\'' +
                ", turma='" + turma + '\'' +
                '}';
    }
}
