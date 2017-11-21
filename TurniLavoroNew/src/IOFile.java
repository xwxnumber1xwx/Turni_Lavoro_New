import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class IOFile {
	
	public boolean ExportToFile(String nameFile, String pathExtern, Dipendente dipendente) {
	boolean yn = false;
	Charset charset = Charset.forName("UTF-8");
	Path path = Paths.get(pathExtern);
	String fileToSave = (dipendente.getNome() + "    " + "NightTariff: " + dipendente.getTotZuSchlag());
	try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
		writer.write(fileToSave, 0, fileToSave.length());
		yn = true;
		System.out.println("File " + nameFile + " creato correttamente" + " \n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String addsToSave = ("  Krank: " + dipendente.getGiorniMalattia() + "  Stufe: " + dipendente.getLivello() + "  Linee: " + dipendente.getLineaLavoro());
	byte data[] = addsToSave.getBytes();
	try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(path, CREATE, APPEND))) { //APPEND aggiunge al file giä esistente altre informazioni
		out.write(data, 0, data.length);
		yn = true;
		System.out.println("File " + nameFile + " AGGIORNATO correttamente" + " \n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (yn == false) {
		System.out.println("File " + nameFile + " non creato o non Aggiornato");
	}
	//File file = new File(nameFile);
	return yn;
	
	}
	
	
}