package kr.co.webcash.utils;

import java.util.Arrays;
import java.util.List;

import difflib.DiffRow;
import difflib.DiffRow.Tag;
import difflib.DiffRowGenerator;

public class PostRevisionUtils {
	
	private static DiffRowGenerator generator = new DiffRowGenerator.Builder()
		.columnWidth(Integer.MAX_VALUE)
		.build();

	public static String generateDiffRows(String original, String revised) {
		return generateDiffRows(Arrays.asList(original.split("\n")), Arrays.asList(revised.split("\n")));
	}

	private static String generateDiffRows(List<String> original, List<String> revised) {
		StringBuilder builder = new StringBuilder();
		
		List<DiffRow> rows = generator.generateDiffRows(original, revised);
		for(DiffRow row : rows){
			
			String oldLine = unnormalize(row.getOldLine());
			String newLine = unnormalize(row.getNewLine());
			
			
			if(row.getTag().equals(Tag.EQUAL)){
				builder.append(oldLine);
			} else if(row.getTag().equals(Tag.INSERT)){
				builder.append("<p class=\"compare_delete\">").append(newLine).append("</p>");
			}else if(row.getTag().equals(Tag.CHANGE)){
				builder.append("<p class=\"compare_delete\">").append(newLine).append("</p>");
				builder.append("<p class=\"compare_insert\">").append(oldLine).append("</p>");
			}else if(row.getTag().equals(Tag.DELETE)){
				builder.append("<p class=\"compare_insert\">").append(oldLine).append("</p>");
			}
			
			builder.append("\n");
		}
		
		return builder.toString();
	}

	private static String unnormalize(String str) {
		return makeTab(htmlEntries(str));
	}

	private static String makeTab(String str) {
		return str.replace("    ", "\t");
	}

	private static String htmlEntries(String str) {
		return str.replace("&lt;", "<").replace("&gt;", ">");
	}
	
}
