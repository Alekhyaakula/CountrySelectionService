# CountrySelectionService

A generic rest based services that can pull list of items that fits for the given criterion -  Filter these options based on the user's input:-All data, if continent is provided then pull list of countries and flag. If countries is provided then send the flag. 

Endpoints - 
/ - Returns all
/country - Returns flag of country
/continent - Returns continent data

Implemented a metrics service that can provide - number of times continent/country flag has been viewed.
Endpoint -
/country/metrics

Performance and Audit logging enhancement to service - Implemented Filter to log responses, performance metrics

