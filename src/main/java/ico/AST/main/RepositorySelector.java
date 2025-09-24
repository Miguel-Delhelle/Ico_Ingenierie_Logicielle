package ico.AST.main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class RepositorySelector extends JFileChooser {
	
	private static final long serialVersionUID = -5216630856700575254L;
	
	private String lePath = "";
	
	public RepositorySelector() {
		super (FileSystemView.getFileSystemView().getHomeDirectory());
    	this.setDialogTitle("Sélectionner UN PROJET JAVA");
    	this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	this.showOpenDialog(this);
    	File selectedRepository = this.getSelectedFile();
    	this.lePath = "file:"+selectedRepository.getAbsolutePath();
		
	}

	public String getLePath() {
		return this.lePath;
	}
	
}
