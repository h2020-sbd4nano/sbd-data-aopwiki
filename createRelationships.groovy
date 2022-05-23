@Grab('com.xlson.groovycsv:groovycsv:1.3')
import static com.xlson.groovycsv.CsvParser.parseCsv
 
fh = new File('kers.csv')
def csv_content = fh.getText('utf-8')
 
def data_iterator = parseCsv(csv_content, separator: ',', readFirstLine: true)

println "PREFIX sbd:   <https://www.sbd4nano.eu/rdf/#>"
println "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
println "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
println "PREFIX dc:    <http://purl.org/dc/elements/1.1/>"
println ""

println "<https://h2020-sbd4nano.github.io/sbd-data-aopwiki/relationships/>"
println " a                    void:DatasetDescription ;"
println " foaf:img             <https://aopwiki.rdf.bigcat-bioinformatics.org/assets/images/aopwiki-snorql-logo.png> ;"
println " dc:source            <https://aopwiki.rdf.bigcat-bioinformatics.org/> ;"
println " dcterms:title        \"AOP-Wiki Key Event Relationships\"@en ;"
println " dcterms:license      <http://creativecommons.org/publicdomain/zero/1.0/> ."
println ""

for (line in data_iterator) {
  if (line[0] != "KER") {
    println "<${line[0]}> a sbdbel:CausalAssertion ;"
    KERtitle = line[0].substring(42)
    println "  rdfs:label \"KER ${KERtitle}\"@en ;"
    println "  dc:source <https://h2020-sbd4nano.github.io/sbd-data-aopwiki/relationships/> ;"
    println "  sbdbel:cause <${line[1]}> ;"
    println "  sbdbel:relationship <https://h2020-sbd4nano.github.io/sbd-data-aopwiki/relationships/> ;"
    println "  sbdbel:outcome <${line[3]}> ;"
    println "  dcterms:license      <http://example.com/unknown> ."
    println ""
    println "  <${line[1]}> rdfs:label \"${line[2]}\"@en ;"
    println "  <${line[3]}> rdfs:label \"${line[4]}\"@en ;"
    println ""
  }
}
