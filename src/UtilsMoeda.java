import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class UtilsMoeda {

	public static double ObterValorDeMoeda(String nomeDaMoedaBase) {
		
		String inputValue;
		
		// regex para se certificar que string pode ser convertida em Double		
		String testeDouble = "(\\d*\\,?\\d+)|(\\d*\\.?\\d+)";
		
		// Loop que obt�m um valor atrav�s de um input
		do { 
			inputValue = JOptionPane.showInputDialog("Por favor insira o valor em " + nomeDaMoedaBase);
			if (!inputValue.matches(testeDouble)) {
				JOptionPane.showMessageDialog(null, "Precisa fornecer um n�mero para ser computado", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while (!inputValue.matches(testeDouble));
		
		// aceita valor de moeda independente se for escrito com ponto ou v�rgula
		if (!(inputValue.indexOf(',') == -1)) {
			inputValue = inputValue.replace(',', '.');
		}
		
		// retorna o valor
		return Double.parseDouble(inputValue);
		
	}
	
	
	// obtem o codigo das moedas
	public static String[] codigoDasMoedas() {
		// Pede a moeda de origem da convers�o
		String[] nomesDasMoedas = UtilsMoeda.nomeDasMoedas();
				
				
		Object moedaOrigem = JOptionPane.showInputDialog(null, "Converter de", "Op��o", JOptionPane.INFORMATION_MESSAGE, null, nomesDasMoedas, nomesDasMoedas[0]);
						
		Object moedaDestino;
				
		do {
			moedaDestino = JOptionPane.showInputDialog(null, "Converter para", "Op��o", JOptionPane.INFORMATION_MESSAGE, null, nomesDasMoedas, nomesDasMoedas[0]);
			if (moedaDestino == moedaOrigem) {
				JOptionPane.showMessageDialog(null, "Precisa escolher moedas diferentes", "AVISO", JOptionPane.ERROR_MESSAGE);
			}
		} while (moedaOrigem == moedaDestino);
				
			
		Map<String, String> moedasSimbolos = UtilsMoeda.ForneceOpcoesMoeda();
				
		String simboloOrigem = moedasSimbolos.get(moedaOrigem);
		String simboloDestino = moedasSimbolos.get(moedaDestino);
					
				
		String codigoParaConversao = simboloOrigem + "_" + simboloDestino;
				
		return new String[] {codigoParaConversao, String.valueOf(moedaOrigem)};
	}
	
	
	
	// devolvem os nomes das moedas
	public static String[] nomeDasMoedas() {
		return new String[]{"Real Brasileiro", "D�lar Americano", "D�lar Canadense", "Euro", "Libra Esterlina", "Ienes Japon�s", "Rand da Africa do Sul", "Peso M�xicano", "Peso Argentino", "Peso Chileno", "Renminbi Chin�s"};
	}
	
	public static String[] codigoDeTodasAsMoedas() {
		return new String[]{"BRL", "USD", "CAD", "EUR", "GBP", "JPY", "ZAR", "MXN", "ARS", "CLP", "CNY"};
	}
	
	
	public static Map<String, String> ForneceOpcoesMoeda() {
		
		// recebe os nomes das moedas e os c�digos
		String[] nomeDasMoedas = nomeDasMoedas();
		String[] codigoDeTodasAsMoedas = codigoDeTodasAsMoedas();
		
		// mapeia os codigos das moedas aos seus respectivos nomes
		Map<String, String> paisesMoedas = new HashMap<String, String>();
		
		for (int i = 0; i < nomeDasMoedas.length; i++) {			
			paisesMoedas.put(nomeDasMoedas[i], new String(codigoDeTodasAsMoedas[i]));
		}
		
		return paisesMoedas;
		
	}
	
	// dois m�todos pegam os nomes das moedas, usam a API e devolvem o valor de convers�o.
	public static String ValorDeConversao(String moedas) {
		
		String APIKEY = "5a7206d06ab76ecdd3a5";	
		String urlCountries = "https://free.currconv.com/api/v7/convert?q=" + moedas + "&compact=ultra&apiKey=" + APIKEY;

		
		String output = getUrlContents(urlCountries);
		return output;
		
	}
	
	private static String getUrlContents(String theURL) {
		
		StringBuilder content = new StringBuilder();
		
		try {
			URL url = new URL(theURL);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
		
	}
	
}
