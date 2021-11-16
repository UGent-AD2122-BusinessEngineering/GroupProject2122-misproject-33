# IS_project

## Installeren IntelliJ
<ol>
<li>Ga naar <a href="https://www.jetbrains.com/idea/download/" target="_blank" rel="noopener">Downloadsite IntelliJ</a></li>
<li>Selecteer Learning or Teaching Programming en juiste versie afhankelijk van jouw computer (exe voor windows en dmg voor mac)

<img src="/figs/IntelliJVersion.png" alt="" title="" width="717" height="207"></li>

<li>Doorloop de installatie procedure

<img src="/figs/installation-options.png" alt="" title="" style="max-width: 100%;" data-d2l-editor-default-img-style="true"></li>

<li>Start IntelliJ op en duid bij alles default aan</li>
<li>De eerste keer dat je een Java project opstart zal IntelliJ ook vragen welke Java SDK (Development kit) je wenst te gebruiken. Indien er geen SDK beschikbaar is kan je aanduiden dat je deze wenst te downloaden.<br><img src="/figs/jdk.png" alt="" title="" width="488" height="477"></li>
</ol>

## Installeren en gebruiken Java FX
<p>JavaFX is geen standaard onderdeel van Java en moet bijgevolg afzonderlijk geïnstalleerd worden en bovendien ook steeds als externe bibliotheek worden toegevoegd aan een project.</p>

### Downloaden JavaFX
<ol>
<li>Ga naar de&nbsp;<a href="https://gluonhq.com/products/javafx/" target="_blank" rel="noopener">JavaFX downloadsite</a></li>
<li>Selecteer en download de juiste versie JavaFX SDK afhankelijk van je computer</li>
<li>Pak het gedownloade bestand uit en bewaar de map op je computer. Je kan dit eventueel doen op dezelfde plaats waar de Java SDK is opgeslagen</li>
</ol>

### Toevoegen JavaFX als Global Library
<p>Indien je wenst gebruik te maken van JavaFX zal je de JavaFX library steeds moeten toevoegen aan je project. Je kan er wel voor zorgen dat de library eenvoudig kan worden toegevoegd door deze library als Global Library toe te voegen aan IntelliJ.</p>
<ol>
<li>Ga naar File &gt; Project Structure</li>
<li>Open onder Platform Settings &gt; Global Libraries en klik op + (New Global Library)</li>
<li>Selecteer Java en het path van de JAVA FX library en daarin de map lib (..../javafx-sdk-12/lib)</li>
<li>De javaFX library is vanaf nu beschikbaar en kan je toevoegen aan je projecten<br><img src="/figs/GlobalLibrary.png" alt="" title="" width="812" height="422"></li>
</ol>

### Toevoegen JavaFX Lib dependency aan een Java IntelliJ project
<ol>
<li>Ga naar File &gt; Project Structure</li>
<li>Open de Modules sectie, tabblad Depencies</li>
<li>Klik op Add (+) &gt; Library</li>
<li>Selecteer de JAVA FX library<br><img src="/figs/dependency.png" alt="" title="" width="734" height="434"><br><br></li>
<li>Pas de wijzigingen toe en sluit het Project Structure dialoogvenster</li>
</ol>

### Toevoegen VM options aan Run configuration</h2>
<p>Alvorens je een GUI FX application kan runnen zal je ook nog een aantal modules moeten toevoegen aan de gebruikte run configuration.</p>
<ol>
<li>Ga naar Run &gt; Edit Configurations. Indien je nog geen run configuration hebt voor je main, kan je deze laten
aanmaken door IntelliJ door je main een eerste keer te runnen.</li>
<li>Selecteer de gebruikte run configuration</li>
<li>Voeg bij VM options de volgende lijn toe: 
<code>--module-path [path van JavaFX lib] --add-modules javafx.controls,javafx.fxml</code>
<br>waarbij <strong>[path van JavaFX lib]</strong> vervangen dient te worden door het pad van de JAVA FX library (..../javafx-sdk-12/lib).<br>Indien het runnen niet lukt, probeer het pad tussen dubbele aanhalingstekens te plaatsen: <strong>".../javafx-sdk-12/lib"</strong>.&nbsp;<br><br><img src="/figs/javaFXmodules.png" alt="" title="" width="787" height="474"></li>
<li>Pas de wijzigingen toe</li>
</ol>

### Installeren Java FX Scene Builder en configureren van IntelliJ
<ul>
<li>Downloaded van JavaFX scene builder van <a href="https://gluonhq.com/products/scene-builder/" target="_blank" rel="noopener">JavaFX downloadsite</a></li>
<li>Installeren JavaFX scene builder</li>
<li>Ga in IntelliJ naar de Settings/Preferences &gt; Languages and Frameworks &gt; JavaFX</li>
<li>Klik op de browse knop en selecteer de Scene Builder app op je computer en klik ok (op een Windows machine is dit waarschijnlijk in de volgende directory: "C:\Program Files\SceneBuilder\SceneBuilder.exe")<br><br><img src="/figs/fx-path-to-executable.png" alt="" title="" width="778" height="290"><br><br></li>
<li>Pas de wijzigingen toe en sluit het dialoogvenster</li>
</ul>

## Installeren MySQL connector
<p>Net zoals voor de installatie van JavaFX zal je de MySQL connector eerst moeten toevoegen als global library en vervolgens toevoegen als dependency aan je project.</p>

###Downloaden MySQL connector
<ol>
<li>Ga naar de <a href="https://dev.mysql.com/downloads/connector/j/" target="_blank" rel="noopener">MySQL downloadsite</a></li>
<li>Selecteer en download de juiste versie MySQL connector afhankelijk van je computer (indien je gebruik maakt van de MySQL Installer op Windows zie de stappen onder het kopje&nbsp; MYSQL Installer (Windows)). 
Apple gebruikers selecteren de platform onafhankelijke versie en pakken deze uit.</li>
<li>Pak het gedownloade bestand uit en bewaar de jar file op je computer. Je kan dit eventueel doen op dezelfde plaats waar de Java SDK en JavaFX libraries zijn opgeslagen</li>
</ol>

### MYSQL Installer (Windows)
<ol>
<li>Installeer de MYSQL Installer</li>
<li>Open de Installer, klik op "Add"</li>
<li>Vervolgens dien je in de lijst met available products te kiezen voor MySQL Connectors &gt; ConnectorJ en deze installeren – deze wordt geïnstalleerd in "C:\Program Files\MySQL\Connector J [versie nummer]")</li>
<li>In deze folder bevindt zich ook de jar file die nodig is in de volgende stap</li>
</ol>
<p><img src="/figs/PastedImage_uf9cvqr7emll317oyrui4zxwtvgbxkxs00128258884.png" width="595" height="451"></p>

### Toevoegen MySQL jar als Global Library
<p>Indien je wenst gebruik te maken van de MySql connector zal je de library steeds moeten toevoegen aan je project. Je kan er wel voor zorgen dat de library eenvoudig kan worden toegevoegd door deze library als Global Library toe te voegen aan IntelliJ.</p>
<ol>
<li>Ga naar File &gt; Project Structure</li>
<li>Open onder Platform Settings &gt; Global Libraries en klik op + (New Global Library)</li>
<li>Selecteer Java en vervolgende de jar</li>
<li>De MySQL connector is vanaf nu beschikbaar en kan je toevoegen aan je projecten<br><img src="/content/enforced/229232-F000887A_2020/GlobalLibrary.png" alt="" title="" width="812" height="422"></li>
</ol>

### Toevoegen MySql connector dependency aan een Java IntelliJ project</h2>
<ol>
<li>Ga naar File &gt; Project Structure</li>
<li>Open de Modules sectie, tabblad Depencies</li>
<li>Klik op Add &gt; Library</li>
<li>Selecteer de MySQL connector<br><img src="/figs/dependency.png" alt="" title="" width="734" height="434"><br><br></li>
<li>Pas de wijzigingen toe en sluit het Project Structure dialoogvenster</li>
</ol>