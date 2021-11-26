@Grab('com.xlson.groovycsv:groovycsv:1.3')
import static com.xlson.groovycsv.CsvParser.parseCsv
 
fh = new File('aops.csv')
def csv_content = fh.getText('utf-8')
 
def data_iterator = parseCsv(csv_content, separator: ',', readFirstLine: true)

println "PREFIX sbd:   <https://www.sbd4nano.eu/rdf/#>"
println "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
println "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
println ""

for (line in data_iterator) {
  println "<${line[0]}> a sbd:Model ;"
  println "  rdfs:label \"${line[1]}\"@en ;"
  println "  dc:source <https://h2020-sbd4nano.github.io/sbd-data-aopwiki/> ;"
  description = line[2].replace("\n"," ").trim()
  println "  dc:description \"${description}\"@en ;"
  println "  foaf:page <${line[0]}> ."
  println ""
}
 
