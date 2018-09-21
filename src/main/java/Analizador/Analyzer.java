package Analizador;

import java.util.ArrayList;

import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;

public class Analyzer {

	Factory factory;

	public Analyzer(String path) {
		Launcher launcher = new Launcher();
		launcher.addInputResource(path);
		launcher.buildModel();
		factory = launcher.getFactory();
	}

	public ArrayList<CtClass> getClasses() {
		ArrayList<CtClass> list = new ArrayList<CtClass>();
		for (CtType c : factory.Class().getAll()) {
			if (c instanceof CtClass<?>) {
				list.add((CtClass<?>) c);
			}
		}
		return list;
	}
	
	public String[] getClassesNames() {
		ArrayList<String> list = new ArrayList<String>();
		for (CtType c : factory.Class().getAll()) {
			if (c instanceof CtClass<?>) {
				list.add(c.getSimpleName());
			}
		}
		return (String[]) list.toArray();
	}
	
	

}
