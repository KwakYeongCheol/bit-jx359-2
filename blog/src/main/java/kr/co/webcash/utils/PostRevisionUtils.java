package kr.co.webcash.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import difflib.DiffRow;
import difflib.DiffRow.Tag;
import difflib.DiffRowGenerator;

public class PostRevisionUtils {
	
	private static DiffRowGenerator generator = new DiffRowGenerator.Builder()
		.columnWidth(Integer.MAX_VALUE)
		.ignoreWhiteSpaces(true)
		.build();

	public static String generateDiffRows(String original, String revised) {
		return generateDiffRows(Arrays.asList(original.split("\n")), Arrays.asList(revised.split("\n")));
	}

	private static String generateDiffRows(List<String> original, List<String> revised) {
		List<DiffRow> rows = generator.generateDiffRows(original, revised);
		
		StringBuilder builder = new StringBuilder();

		Tag beforeTag = null;
		List<DiffRow> tmpDiffRows = new LinkedList<DiffRow>();
		
		for(DiffRow row : rows){
			Tag currentTag = row.getTag();
			
			if(!currentTag.equals(beforeTag)){
				if(beforeTag != null){
					builder.append(getRows(tmpDiffRows));
					tmpDiffRows = new LinkedList<DiffRow>();
				}
			}
			
			beforeTag = currentTag;
			tmpDiffRows.add(row);
		}
		
		builder.append(getRows(tmpDiffRows));
		
		return builder.toString();
	}
	
	private static String getRows(List<DiffRow> diffRows){
		StringBuilder builder = new StringBuilder();
		
		StringBuilder tagChangeInsert = new StringBuilder(); 
		
		if(diffRows.size() > 0){
			Tag currentTag = diffRows.get(0).getTag();
			
			builder.append(getPrefixByTag(currentTag));
			for(DiffRow row : diffRows){
				String oldLine = unnormalize(row.getOldLine());
				String newLine = unnormalize(row.getNewLine());
				
				switch(currentTag){
				case EQUAL:
					builder.append(oldLine);
					break;
				case INSERT:
					builder.append(newLine);
					break;
				case CHANGE:
					builder.append(newLine);
					tagChangeInsert.append(oldLine);
					break;
				case DELETE:
					builder.append(oldLine);
					break;
				}
				
				builder.append("\n");
			}
			
			builder.append(getSuffixByTag(currentTag));
			
			if(currentTag.equals(Tag.CHANGE)){
				builder.append("<div class=\"compare_insert\">").append(tagChangeInsert).append("</div>");
			}
		}
		
		return builder.toString();
	}


	private static String getPrefixByTag(Tag currentTag) {
		switch(currentTag){
		case INSERT:
		case CHANGE:
			return "<div class=\"compare_delete\">";
		case DELETE:
			return "<div class=\"compare_insert\">";
		case EQUAL:
		default:
			return "";
		}
	}
	
	private static Object getSuffixByTag(Tag currentTag) {
		switch(currentTag){
		case INSERT:
		case CHANGE:
		case DELETE:
			return "</div>";
		case EQUAL:
		default:
			return "";
		}
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