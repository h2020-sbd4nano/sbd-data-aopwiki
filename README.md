# sbd-data-aopwiki

## Getting the AOP models

Run the following commmands to create the `sbd:Model`s:

```shell
curl -H "Accept: text/csv" --data-urlencode query@sparql/aops.rq -G https://aopwiki.rdf.bigcat-bioinformatics.org/sparql -o aops.csv
groovy createModels.groovy | tee aops.ttl
```

The used query is based on the below query for adverse outcomes.

## AOP-Wiki SPARQL queries for datasets:

AOP-AO:
```sparql
SELECT ?AOP ?AOPTitle ?AO ?AOTitle WHERE {
?AOP a aopo:AdverseOutcomePathway ;
dc:title ?AOPTitle ;
aopo:has_adverse_outcome ?AO.
?AO dc:title ?AOTitle .}
```

AOP-KE:
```sparql
SELECT ?AOP ?AOPTitle ?KE ?KETitle WHERE {
?AOP a aopo:AdverseOutcomePathway ;
dc:title ?AOPTitle ;
aopo:has_key_event ?KE.
?KE dc:title ?KETitle .}
```

MIE-KE:
```sparql
SELECT ?MIE ?MolecularInitiatingEventName ?KE ?KeyEventName WHERE {
?MIE dc:title ?MolecularInitiatingEventName .
?AOP a aopo:AdverseOutcomePathway ;
aopo:has_molecular_initiating_event ?MIE;
aopo:has_key_event ?KE.
?KE dc:title ?KeyEventName .
FILTER (?MIE != ?KE)}
```
