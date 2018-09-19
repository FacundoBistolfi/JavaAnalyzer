package pruebacodeanalyzer.JavaAnalyzer;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import spoon.Launcher;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtCase;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

public class App {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.addInputResource("C:\\Users\\FacundoBistolfi\\Downloads\\java\\claseprueba.java");
		launcher.buildModel();
		//CtModel model = launcher.getModel();
		Factory factory = launcher.getFactory();
		for(CtType c : factory.Class().getAll()){
			//System.out.println("Class: " + c.getQualifiedName());
			System.out.println("Class: " + c.getSimpleName());
			if(c instanceof CtClass<?>){
			    CtClass<?> clas = (CtClass<?>)c;
			    System.out.println("- All methods");
			    for(CtMethod m : clas.getMethods()) {
			    	int cc = 1;
					System.out.println("  - Method: (" + m.getType() +") "+ m.getSimpleName());
					//Cantidad Asignaciones
					int cantAsig = m.getElements(new TypeFilter(CtAssignment.class)).size();
					if(cantAsig > 0) System.out.println("    - Cant asignaciones: " + cantAsig);
					//Cantidad de IF
					ArrayList<CtIf> ifList = (ArrayList<CtIf>) m.getElements(new TypeFilter(CtIf.class));
					if(ifList.size()>0) {
						System.out.println("    - Cant IF: " + ifList.size());
						int cantOr = 0, cantAnd = 0;
						for(CtIf i : ifList) {
							cantOr  += StringUtils.countMatches(i.getCondition().toString(), "||");
							cantAnd += StringUtils.countMatches(i.getCondition().toString(), "&&");
						}
						System.out.println("    -   Cant And: " + cantAnd);
						System.out.println("    -   Cant Or: " + cantOr);
						cc += (ifList.size() + cantAnd + cantOr);
					}
					//Cantidad de Ternarios
					ArrayList<CtConditional> condList = (ArrayList<CtConditional>)m.getElements(new TypeFilter(CtConditional.class));
					if(condList.size()>0) {
						System.out.println("    - Cant TERNARY: " + condList.size());
						int cantOr = 0, cantAnd = 0;
						for(CtConditional i : condList) {
							cantOr  += StringUtils.countMatches(i.getCondition().toString(), "||");
							cantAnd += StringUtils.countMatches(i.getCondition().toString(), "&&");
						}
						System.out.println("    -   Cant And: " + cantAnd);
						System.out.println("    -   Cant Or: " + cantOr);
						cc += (condList.size() + cantAnd + cantOr);
					}
					//Cantidad de FOR
					int cantFor =  m.getElements(new TypeFilter(CtFor.class)).size();
					if(cantFor > 0)System.out.println("    - Cant FOR: " + cantFor);
					cc += cantFor;
					//Cantidad de FOREACH
					int cantForeach = m.getElements(new TypeFilter(CtForEach.class)).size();
					if(cantForeach > 0) System.out.println("    - Cant FOREACH: " + cantForeach);
					cc += cantForeach;
					//Cantidad de DO					
					ArrayList<CtDo> doList = (ArrayList<CtDo>)m.getElements(new TypeFilter(CtDo.class));
					if(doList.size()>0) {
						System.out.println("    - Cant DO: " + doList.size());
						int cantOr = 0, cantAnd = 0;
						for(CtDo i : doList) {
							cantOr  += StringUtils.countMatches(i.getLoopingExpression().toString(), "||");
							cantAnd += StringUtils.countMatches(i.getLoopingExpression().toString(), "&&");
						}
						System.out.println("    -   Cant And: " + cantAnd);
						System.out.println("    -   Cant Or: " + cantOr);
						cc += (doList.size() + cantAnd + cantOr);
					}
					//Cantidad de WHILE
					ArrayList<CtWhile> whileList = (ArrayList<CtWhile>)m.getElements(new TypeFilter(CtWhile.class));
					if(whileList.size()>0) {
						System.out.println("    - Cant WHILE: " + whileList.size());
						int cantOr = 0, cantAnd = 0;
						for(CtWhile i : whileList) {
							cantOr  += StringUtils.countMatches(i.getLoopingExpression().toString(), "||");
							cantAnd += StringUtils.countMatches(i.getLoopingExpression().toString(), "&&");
						}
						System.out.println("    -   Cant And: " + cantAnd);
						System.out.println("    -   Cant Or: " + cantOr);
						cc += (whileList.size() + cantAnd + cantOr);
					}
					//Cantidad de SWITCH
					int cantSwitch = m.getElements(new TypeFilter(CtSwitch.class)).size();
					if(cantSwitch>0) System.out.println("    - Cant SWITCH: " + cantSwitch);	
					//Cantidad de CASE
					int cantCase = m.getElements(new TypeFilter(CtCase.class)).size();
					if(cantCase>0) System.out.println("    - Cant CASE: " + cantCase);	
					cc += cantCase;		
					System.out.println("  - CC: "+cc);

			    }
			    System.out.println("- All fields");
			    for(CtField f : clas.getFields()) {
					System.out.println("  - Field: (" + f.getType() +")"+ f.getSimpleName());
			    }
			    
		   }
		}
	}
}
