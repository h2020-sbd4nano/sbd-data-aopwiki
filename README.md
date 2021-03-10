# sbd-data-aopwiki


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
