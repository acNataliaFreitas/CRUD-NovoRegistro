var array = [];
var headers = [];

$('#atualiza-conta').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var id = button.data('id') // Extract info from data-* attributes
	var modal = $(this)
	
	modal.find('.modal-content #codigo').val(array[id].ID)
	modal.find('.modal-content #titular').val(array[id].Titular)
	modal.find('.modal-content #conta').val(array[id].Conta)
	modal.find('.modal-content #agencia').val(array[id].Agência)
	modal.find('.modal-content #limite').val(array[id].Limite)
	modal.find('.modal-content #saldo').val(array[id].Saldo)
})


$('#exclui-conta').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var id = button.data('id') // Extract info from data-* attributes
	var modal = $(this)
	modal.find('.modal-content #codigo').val(id)
})

$('#tabela-dados .campos').each(function(index, item) {
    headers[index] = $(item).html();
});

$('#tabela-dados tr').has('td').each(function() {
    var arrayItem = {};
    $('.dados', $(this)).each(function(index, item) {
        arrayItem[headers[index]] = $(item).html();
    });
    array.push(arrayItem);
});