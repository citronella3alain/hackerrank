import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    // Complete the membersInTheLargestGroups function below.
    static void membersInTheLargestGroups(int n, int m, int a, int b, int f, int s, int t) {
        // Print the names of the students in all largest groups or determine if there are no valid groups.
        Students students = new Students();
        for (int i = 0; i < n; i++){
		String[] str = scanner.nextLine().split(" ");
		students.addStudent(str[0], new Student(str[0], Integer.parseInt(str[1])));	
        }
	System.out.println(students);
	/*
	for (int i = 0; i < m; i++){
		
	}
	*/

	// Test Student and Students classes
	/*
	Students students = new Students();
	students.addStudent("Allen", new Student("Allen", 12));
	students.addStudent("Sylvia", new Student("Sylvia", 10));
	System.out.println(students);
	Groups groups = new Groups();
	int loc1 = groups.addGroup(new Group());
	int loc2 = groups.addGroup(new Group());
	groups.getGroup(loc1).addStudent(students.getStudent("Allen"));
	groups.getGroup(loc2).addStudent(students.getStudent("Sylvia"));
	System.out.println(groups);
	*/
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nmabfst = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nmabfst[0]);

        int m = Integer.parseInt(nmabfst[1]);

        int a = Integer.parseInt(nmabfst[2]);

        int b = Integer.parseInt(nmabfst[3]);

        int f = Integer.parseInt(nmabfst[4]);

        int s = Integer.parseInt(nmabfst[5]);

        int t = Integer.parseInt(nmabfst[6]);

        membersInTheLargestGroups(n, m, a, b, f, s, t);

        scanner.close();
    }
}

class Students {
	private HashMap <String, Student> students;
	public Students(){
		students = new HashMap <String, Student>();
	}
	public Student getStudent(String name){
		return students.get(name);
	}
	public void addStudent (String name, Student student){
		students.put(name, student);
	}
	public String toString(){
		String text = "Key \t Value \n";
		Iterator it = students.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			text += (me.getKey() + " \t " + me.getValue() + " \n ");
		}
		return text;
	}
}

class Student {
	private String name;
	private int gradeLevel;
	private int groupID;
	public Student (String name, int gradeLevel){
		this.name = name;
		this.gradeLevel = gradeLevel;
	}
	public String getName(){
		return name;
	}
	public int getGradeLevel(){
		return gradeLevel;
	}
	public String toString(){
		return this.name + " is in Grade " + this.gradeLevel + " and in group # " + groupID;
	}
	public void setGroupID(int groupID){
		this.groupID = groupID;
	}
	public int getGroupID(){
		return groupID;
	}
}

class Groups {
	private ArrayList <Group> groups;
	public Groups() {
		groups = new ArrayList<Group>();
	}
	public int addGroup (Group group){
		groups.add(group);
		group.setGroupID(groups.size() - 1);
		return groups.size() - 1;
	}
	public Group getGroup (int id){
		return groups.get(id);
	}
	public void removeGroup (Group group){
		groups.remove(group);
		for (int i = 0; i < groups.size(); i++){
			if (i != groups.get(i).getGroupID()){
				groups.get(i).setGroupID(i);
			}
		}
	}
	public int indexOfStudentSGroup (Student student){
		for (int i = 0; i < groups.size(); i++){
			if (groups.get(i).containsStudent(student)){
				return i;
			}
		}
		System.out.println("ERROR: Student could not be found in any group");
		return -1;
	}
	public String toString(){
		String text = "Index: \t Group \n";
		for (Group group : groups){
			text += group;
		}
		return text;
	}
}

class Group {
	private HashMap<String, Student> students;
	static int maxSize;	//the maximum size of a group
	static int minSize;	// the minimum size of a group
	private int[] gradeCount; // current number of students in group per grade level
	static int[] maxGradeCount; // maximum number of students in a group per grade level
	private int groupID;

	public Group(){
		students = new HashMap<String, Student>();
		gradeCount = new int[]{0, 0, 0};
	}
	public void setGroupID(int id){
		groupID = id;
	}
	public int getGroupID(){
		return groupID;
	}
	public boolean containsStudent(Student student){
		return students.containsValue(student);
	}
	public void addStudent(Student student){ //TODO Case student is already part of other groups
		students.put(student.getName(), student);
		student.setGroupID(groupID);
	}
	public String toString(){
		String text = "Group ID " + groupID + " \t Max/min sizes: " + maxSize + " " + minSize + " \t Grade Count: " + Arrays.toString(gradeCount) + " \n";
		Iterator it = students.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			text += (" \t " + me.getKey() + " \t " + me.getValue() + " \n ");
		}
		return text;
	}
}
