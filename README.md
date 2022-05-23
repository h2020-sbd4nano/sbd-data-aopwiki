# sbd-data-aopwiki

The SPARQL endpoint used in this repository is described in this paper: https://doi.org/10.1089/aivt.2021.0010

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

## AOP-Wiki SPARQL for causal relationships (KERs)

```sparql
SELECT ?KER ?KEup ?KEuptitle ?KEdown ?KEdowntitle WHERE { 
  ?KER a aopo:KeyEventRelationship ; 
  aopo:has_upstream_key_event ?KEup; aopo:has_downstream_key_event ?KEdown.
  ?KEup dc:title ?KEuptitle .
  ?KEdown dc:title ?KEdowntitle .
}
```
