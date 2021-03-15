# Esercitazioni

- la fetch dei prodotti dal db ci mette dai 3 ai 5 secondi prima di tornare un risultato utile,
inserisci una ProgressBar circolare indeterminata e collegala alla variabili isLoading del viewmodel.
La progress bar deve essere visibile durante l'operazione e scomparire quando la lista si popola.

- crea una seconda activity ProductDetailActivity, aggiungi un onClickListener all'adapter dei prodotti.
Ogni volta che viene cliccato un prodotto l'app deve lanciare la nuova activity per visualizzarne i dettagli.
Per recuperare i dati del prodotto nell'activity del dettaglio passati l'id del prodotto come
parametro all'interno di un Bundle, in questo modo puoi riutilizzarlo per recuperare nuovamente i
dettagli dal database. Ovviamente dovrai aggiungere un metodo al ProductDao per recuperare un prodotto
in base all'id. Per ora lascia perdere Room, lo useremo più avanti.

- Rinominare l'activity ProductsActivity in ShopActivity.
Trasformare in fragment ProductActivity -> ProductsFragment e ProductDetailActivity -> ProductDetailFragment
Utilizzare il navigator per navigare tra i due fragment e passare l'id del prodotto tramite il plugin safeArgs.
Definire un grafo di navigazione e inserire nell'activity principale un NavHostFragment.
Inserire nel fragment ProductDetail un pulsante per tornare alla lista dei prodotti.


- quando il device è un telefono in potrait visualizzi gli item dell'adapter come una lista.
quando il device è un telefono in landscape visualizzi gli item a griglia (2 colonne).
quando il device è un tablet (portrait/landscape) mostri gli item in una griglia 2 colonne.
definire dimen dei font, dei margini, padding, etc per tablet
aggiungere supporto alle immagini dei prodotti (formato vettoriale)

- Sistemare il codice seguendo i commenti che ho lasciato (vedi da git le diff del commit per trovarli).
Visualizzare i prodotti su 3 colonne in configurazione TABLET + LANDSCAPE.
Aggiungere immagine grande nel fragment del dettaglio del prodotto.
Creare un database con Room. All'apertura dell'app se il db non esiste, va creato e riempito con i
questi prodotti:

[
    {
       "id":1,
       "name":"Beer",
       "img":"img_beer"
    },
    {
       "id":2,
       "name":"Ice cream",
       "img":"img_ice_cream"
    },
    {
       "id":3,
       "name":"Lemonade",
       "img":"img_lemonade"
    },
    {
       "id":4,
       "name":"Ice pop",
       "img":"img_ice_pop"
    },
    {
       "id":5,
       "name":"Watermelon",
       "img":"img_watermelon"
    }
 ]
 
Se il db esiste già, vuol dire che i prodotti sono già dentro e non c'è bisogno di fare niente.
Modificare i metodi del dao per recuperare la lista dei prodotti e un singolo prodotto tramite id.
Per le immagini crea un package util e al suo interno una Mappa<String, Int> per recuperare
le immagini vettoriali tramite il loro id a partire da product.img