package Analizador;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class EjemploUsoAnalyzer {

	//Poner el path de archivo relativo, yo no se como hacerlo jajaajajajaja :C
	
	public static void main(String[] args) {
		McCabeAnalyzer mc = new McCabeAnalyzer("C:\\Users\\FacundoBistolfi\\eclipse-Turner-workspace\\JavaAnalyzer\\src\\test\\java\\TestFiles\\Prueba1.java");
		CtClass<?> c = mc.getClasses().get(0);
		for(CtClass cl : mc.getClasses(c)) {
			System.out.println("Clase "+cl.getSimpleName());
			System.out.println("Metodos y complejidad: ");
			for(CtMethod m : mc.getMethods(cl)) {
				System.out.println("- "+m.getSimpleName()+" ("+mc.getCiclomaticComplexity(m)+")");
			}
		}
	}
}
