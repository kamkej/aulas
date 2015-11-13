package br.com.tads.co.cassorganizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julio on 11/13/15.
 */
public class studentOperations {

    private BDWrapper bdWrapper;
    private String [] STUDENT_COLUMNS = {BDWrapper.TABLE_ID,BDWrapper.NOME,BDWrapper.NOTA,BDWrapper.TURMA,BDWrapper.STATUS};
    private SQLiteDatabase database;

    public studentOperations(Context context) {
        bdWrapper = new BDWrapper(context);
    }
    public void open() throws SQLException{
        database = bdWrapper.getWritableDatabase();
    }
    public void close(){
        bdWrapper.close();
    }
    public Student addStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(bdWrapper.NOME,student.getName());
        values.put(bdWrapper.NOTA,student.getNota());
        values.put(bdWrapper.TURMA,student.getTurma());
        values.put(bdWrapper.STATUS, student.getStatus());

        long studId = database.insert(bdWrapper.TABLE,null,values);

        Cursor cursor =database.query(BDWrapper.TABLE, STUDENT_COLUMNS, BDWrapper.TABLE_ID + " = "
                + studId, null, null, null, null);
        cursor.moveToFirst();
        Student student1 = parseStudent(cursor);
        cursor.close();
        return student1;
    }

    public List getAllStudents(){
        List students = new ArrayList();

        Cursor cursor = database.query(BDWrapper.TABLE,STUDENT_COLUMNS,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
         cursor.close();
        return  students;
    }

    private Student parseStudent(Cursor cursor) {

        Student student = new Student();
        student.setId(cursor.getInt(0));
        student.setName(cursor.getString(1));
        student.setNota(cursor.getFloat(2));
        student.setTurma(cursor.getString(3));
        student.setStatus(cursor.getString(4));

        return student;
    }
}
