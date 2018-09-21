package Analizador;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import spoon.Launcher;
import spoon.reflect.code.CtCase;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

public class McCabeAnalyzer {

	Factory factory;

	public McCabeAnalyzer(String path) {
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<CtClass> getClasses(CtClass myClass) {
		return (ArrayList<CtClass>) myClass.getElements(new TypeFilter(CtClass.class));
	}
	/*
	 * public String[] getClassesNames() { ArrayList<String> list = new
	 * ArrayList<String>(); for (CtType c : factory.Class().getAll()) { if (c
	 * instanceof CtClass<?>) { list.add(c.getSimpleName()); } } return (String[])
	 * list.toArray(); }
	 */

	public Set<CtMethod> getMethods(CtClass myClass) {
		Set<CtMethod> list = (Set<CtMethod>) myClass.getMethods();
		return list;
	}

	/*
	 * public String[] getMethodsNames(CtClass<?> myClass) { ArrayList<String> list
	 * = new ArrayList<String>(); for (CtMethod<?> m : this.getMethods(myClass)) {
	 * list.add(m.getSimpleName()); } return (String[]) list.toArray(); }
	 */

	public int getCiclomaticComplexity(CtMethod method) {
		int cc = 1;
		// Cantidad de IF
		ArrayList<CtIf> ifList = (ArrayList<CtIf>) method.getElements(new TypeFilter(CtIf.class));
		if (ifList.size() > 0) {
			for (CtIf i : ifList) {
				cc += StringUtils.countMatches(i.getCondition().toString(), "||");
				cc += StringUtils.countMatches(i.getCondition().toString(), "&&");
			}
			cc += ifList.size();
		}
		// Cantidad de Ternarios
		ArrayList<CtConditional> condList = (ArrayList<CtConditional>) method
				.getElements(new TypeFilter(CtConditional.class));
		if (condList.size() > 0) {
			for (CtConditional i : condList) {
				cc += StringUtils.countMatches(i.getCondition().toString(), "||");
				cc += StringUtils.countMatches(i.getCondition().toString(), "&&");
			}
			cc += condList.size();
		}
		// Cantidad de FOR
		cc += method.getElements(new TypeFilter(CtFor.class)).size();
		// Cantidad de FOREACH
		cc += method.getElements(new TypeFilter(CtForEach.class)).size();
		// Cantidad de DO
		ArrayList<CtDo> doList = (ArrayList<CtDo>) method.getElements(new TypeFilter(CtDo.class));
		if (doList.size() > 0) {
			for (CtDo i : doList) {
				cc += StringUtils.countMatches(i.getLoopingExpression().toString(), "||");
				cc += StringUtils.countMatches(i.getLoopingExpression().toString(), "&&");
			}
			cc += doList.size();
		}
		// Cantidad de WHILE
		ArrayList<CtWhile> whileList = (ArrayList<CtWhile>) method.getElements(new TypeFilter(CtWhile.class));
		if (whileList.size() > 0) {
			for (CtWhile i : whileList) {
				cc += StringUtils.countMatches(i.getLoopingExpression().toString(), "||");
				cc += StringUtils.countMatches(i.getLoopingExpression().toString(), "&&");
			}
			cc += whileList.size();
		}
		
		
		// Cantidad de CASE
		cc += (method.getElements(new TypeFilter(CtCase.class)).size());
		// Cantidad de SWITCH
		cc -= (method.getElements(new TypeFilter(CtSwitch.class)).size());

		return cc;
	}

}
