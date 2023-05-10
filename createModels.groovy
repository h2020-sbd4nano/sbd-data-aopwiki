@Grab('com.xlson.groovycsv:groovycsv:1.3')
import static com.xlson.groovycsv.CsvParser.parseCsv
 
fh = new File('aops.csv')
def csv_content = fh.getText('utf-8')
 
def data_iterator = parseCsv(csv_content, separator: ',', readFirstLine: true)

println "PREFIX sbd:   <https://www.sbd4nano.eu/rdf/#>"
println "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
println "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
println "PREFIX dc:    <http://purl.org/dc/elements/1.1/>"
println "PREFIX pav:     <http://purl.org/pav/>"
println ""

println "<https://h2020-sbd4nano.github.io/sbd-data-aopwiki/models/>"
println " a                    void:DatasetDescription ;"
println " foaf:img             <https://aopwiki.rdf.bigcat-bioinformatics.org/assets/images/aopwiki-snorql-logo.png> ;"
println " dc:source            <https://aopwiki.rdf.bigcat-bioinformatics.org/> ;"
println " dcterms:title        \"AOP-Wiki AOPs\"@en ;"
println " dcterms:description  \"Models based on the AOP-Wiki AOPs that predict that even certain KEs happen, that the AO also happens\"@en ;"
println " dcterms:publisher    <https://github.com/h2020-sbd4nano/sbd-data-aopwiki> ;"
println " pav:createdBy        <https://github.com/h2020-sbd4nano/sbd-data-aopwiki> ;"
println " dcterms:license      <http://creativecommons.org/publicdomain/zero/1.0/> ."
println ""

for (line in data_iterator) {
  if (line[1] != "AOPTitle") {
    println "<${line[0]}> a sbd:Model ;"
    println "  rdfs:label \"${line[1]}\"@en ;"
    println "  dc:source <https://h2020-sbd4nano.github.io/sbd-data-aopwiki/models/> ;"
    description = line[2].replace("\n"," ").replace("\"", "\\\"").trim()
    println "  dc:description \"${description}\"@en ;"
    println "  foaf:page <${line[0]}> ;"
    println "  dcterms:license      <https://creativecommons.org/licenses/by/4.0/> ."
    println ""
  }
}
 
