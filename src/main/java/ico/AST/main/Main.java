package ico.AST.main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
    	
        String[] allSourceCode = new PathToText(new RepositorySelector().getLePath()).getAllJavaContent();

        ASTParser parser = ASTParser.newParser(AST.JLS21);
        
        for (String sourceCode : allSourceCode) {
        	
	        parser.setSource(sourceCode.toCharArray());
	        parser.setKind(ASTParser.K_COMPILATION_UNIT);
	        
	        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	        cu.accept(new ASTVisitor() {
	
	            @Override
	            public boolean visit(TypeDeclaration node) {
	                String className = node.getName().getFullyQualifiedName();
	                System.out.println("Classe trouvée : " + className);
	                System.out.println(node.getLength());
	                return true;
	            }
	
	            @Override
	            public boolean visit(MethodDeclaration node) {
	                String methodName = node.getName().getIdentifier();
	                System.out.println("----Méthode trouvée: " + methodName+" Retourne "+node.getReturnType2());
	                //System.out.println("----Type de retour : " + node.getReturnType2());
	                return true;
	            }
	            
	            @Override
	        	public boolean visit(MethodInvocation node) {
	            	System.out.println("---------"+node.getName());
	            	
	        		return false;
	        	}
	        });
        }
    }
}