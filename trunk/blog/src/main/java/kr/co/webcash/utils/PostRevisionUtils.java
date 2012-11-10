package kr.co.webcash.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import difflib.DiffRow;
import difflib.DiffUtils;
import difflib.Patch;
import difflib.PatchFailedException;
import difflib.DiffRow.Tag;
import difflib.DiffRowGenerator;

public class PostRevisionUtils {
	
	public static String patch(String contents, String diff){
		List<String> patchList = Arrays.asList(diff.split("\n"));
		Patch patch = DiffUtils.parseUnifiedDiff(patchList);
		StringBuilder builder = new StringBuilder();
		
		boolean start = true;
		try{
			for(Object obj : DiffUtils.patch(Arrays.asList(contents.split("\n")), patch)){
				if(start)		start = false;
				else			builder.append("\n");
				builder.append(obj);
			}
			
			return builder.toString();
		}catch(PatchFailedException e){
			System.out.println("patch failed : contents = " + contents + ", diff = " + diff);
			return "";
		}
	}
	
	public static String generateDiff(String original, String patched){
		List<String> originalList = Arrays.asList(original.split("\n"));
		List<String> patchedList = Arrays.asList(patched.split("\n"));
		
		final Patch patch = DiffUtils.diff(originalList, patchedList);
		List<String> unifiedDiff = DiffUtils.generateUnifiedDiff(null, null, originalList, patch, 0);
		
		StringBuilder builder = new StringBuilder();
		for(String line : unifiedDiff){
			builder.append(line).append("\n");
		}
		
		return builder.toString();
	}
	
	/* Diff Row */
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