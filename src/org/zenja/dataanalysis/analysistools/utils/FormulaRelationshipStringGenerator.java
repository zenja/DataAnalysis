package org.zenja.dataanalysis.analysistools.utils;

import org.zenja.dataanalysis.analysistools.enums.FormulaRelationship;

public class FormulaRelationshipStringGenerator {
	public static String generate(FormulaRelationship fr) {
		switch (fr) {
		case EQ:
			return "=";
		case NEQ:
			return "!=";
		case LEQ:
			return "<=";
		case GEQ:
			return ">=";
		case LT:
			return "<";
		case GT:
			return ">";
		default:
			return "=";
		}
	}
}
