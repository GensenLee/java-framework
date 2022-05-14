package org.devops.core.utils.constant;

public enum FileType {

	MPG("mpg","video/mpg"),
	MPEG("mpeg","video/mpg"),
	AVI("avi","video/avi"),
	WMV("wmv","video/x-ms-wmv"),
	MP4("mp4","video/mpeg4"),
	JPEG("jpg","image/jpeg"),
	PNG("png","image/png"),
	GIF("gif","image/gif"),
	XML("xml","text/xml"),
	TXT("txt","text/plain"),
	PDF("pdf","application/pdf"),
	XLS("xls","application/vnd.ms-excel"),
	XLSX("xlsx","application/vnd.ms-excel"),
	DOC("doc","application/vnd.ms-word"),
	DOCX("docx","application/vnd.ms-word"),
	ZIP("zip","application/octet-stream"),
	UNKNOWN("","application/octet-stream");

	private String contentType = "";
	private String type = "";

	private FileType(String type, String contentType){
		this.contentType = contentType;
		this.type = type;
	}

	public String getContentType(){
		return this.contentType;
	}

	public String getType(){
		return this.type;
	}

	public boolean isVideo(){
		if(this.type.equalsIgnoreCase("mpg")
				|| this.type.equalsIgnoreCase("mpeg")
				|| this.type.equalsIgnoreCase("wmv")
				|| this.type.equalsIgnoreCase("mp4")
				|| this.type.equalsIgnoreCase("avi")){
			return true;
		}
		return false;
	}

	public boolean isImage(){
		if(this.type.equalsIgnoreCase("png")
				|| this.type.equalsIgnoreCase("jpg")
				|| this.type.equalsIgnoreCase("gif")){
			return true;
		}
		return false;
	}

	public static FileType get(String file){
		String fileName = file.split("@")[0];
		String[] tmp = fileName.split("\\.");
		if(tmp.length <= 1){
			return FileType.UNKNOWN;
		}
		String type = tmp[tmp.length-1];

		for(FileType fileViewType : FileType.values()){
			if(fileViewType.getType().equalsIgnoreCase(type)){
				return fileViewType;
			}
		}
		return FileType.UNKNOWN;
	}

	public static String getExtPath(String ext){
		if(ext == null){
			ext = "";
		}
		ext = ext.trim().toLowerCase().replaceFirst("\\.", "");
		if(ext.equals("png") || ext.equals("jpg")
				|| ext.equals("jpeg") || ext.equals("gif") || ext.equals("tiff")
		){
			return "image";
		}else if(ext.equals("txt") || ext.equals("data") || ext.equals("info") || ext.equals("pdf")
				|| ext.equals("doc") || ext.equals("docx")
				|| ext.equals("xls") || ext.equals("xlsx")
				|| ext.equals("ppt") || ext.equals("pptx") ){
			return "document";
		}else if(ext.equals("mp3") || ext.equals("ape") || ext.equals("wma") || ext.equals("wav") || ext.equals("flac") || ext.equals("aac") || ext.equals("ogg")){
			return "sound";
		}else if(ext.equals("mp4") || ext.equals("avi") || ext.equals("rm")
				|| ext.equals("rmvb") || ext.equals("wmv") || ext.equals("mov") || ext.equals("vob")
				|| ext.equals("3gp") || ext.equals("flv") || ext.equals("mkv")){
			return "video";
		}else if (ext.equals("zip")){
			return "archive";
		}else {
			return "unknown";
		}
	}
}
