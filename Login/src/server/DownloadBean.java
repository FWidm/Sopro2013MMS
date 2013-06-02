package server;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "DownloadBean")
@SessionScoped
public class DownloadBean {
	private String pathToFile;
	private StreamedContent file;

	public DownloadBean() {
		pathToFile="AlgorithmenundDatenstrukturen_v1.pdf";
		
		InputStream stream = this.getClass().getResourceAsStream(pathToFile);
		System.out.println("stream is null: "+(stream==null));
		file = new DefaultStreamedContent(stream, "pdf",
				pathToFile);
		System.out.println(file==null);
	}
	
	/**
	 * prepares the stream to reopen with the needed content.
	 * @param e
	 */
	public void prepareStream(ActionEvent e){
		pathToFile="AlgorithmenundDatenstrukturen_v1.pdf";
		
		InputStream stream = this.getClass().getResourceAsStream(pathToFile);
		System.out.println("stream is null: "+(stream==null));
		file = new DefaultStreamedContent(stream, "pdf",
				pathToFile);
		System.out.println(file==null);
	}

	public StreamedContent getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}
}
