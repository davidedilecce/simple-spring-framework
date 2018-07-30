$(document).ready(function() {

    $("#savePersonButton").on("click", function() {

        var id = $("[name=id]").val();
        var name = $("[name=name]").val();
        var surname = $("[name=surname]").val();
        var age = parseInt($("[name=age]").val());

        if (!name || name == "") {
            alert("nome non specificato");
            return;
        }

        if (!surname || surname == "") {
            alert("cognome non specificato");
            return;
        }

        if (!age || isNaN(age)) {
            alert("età non specificata o non valida");
            return;
        }

        $.ajax({
            url: BASE + "/save",
            type: "POST",
            data: JSON.stringify({id: id, name: name, surname: surname, age: age}),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: "json",
            success: function(resp, status, xhr) {
                location.reload()
            },
            error(xhr,status,error) {
            }
        });

    });


    $(".removePerson").on("click", function() {
        let id = parseInt($(this).attr("data-id"));
        $.ajax({
            url: BASE + "/" + id,
            type: "DELETE",
            headers: {
                'Accept': 'application/json',
            },
            success: function(resp, status, xhr) {
                alert("Operazione completata con successo!");
                $("#personsTable").find("tbody").find("tr[data-id=" + id + "]").remove();
            },
            error(xhr,status,error) {
            }
        });
    });

    $(".updatePerson").on("click", function() {

        var tr = $("tr[data-id=" + $(this).attr("data-id") + "]");

        var id = $(this).attr("data-id");
        var name = $(tr).find("td:nth(1)").text();
        var surname = $(tr).find("td:nth(2)").text();
        var age = $(tr).find("td:nth(3)").text();

        $("[name=id]").val(id);
        $("[name=name]").val(name);
        $("[name=surname]").val(surname);
        $("[name=age]").val(age);

    });

    $("[name=filterName]").on("input", function() {

        let name = $(this).val();

        $.ajax({
            url: BASE + "/find?name=" + name,
            type: "GET",
            success: function(resp, status, xhr) {
                $("#personsTable").find("tbody").empty();
                for (var i = 0; i < resp.value.length; i++) {
                    $("#personsTable").find("tbody").append('' +
                        '<tr>' +
                        '<td>' + resp.value[i].id + '</td>' +
                        '<td>' + resp.value[i].name + '</td>' +
                        '<td>' + resp.value[i].surname + '</td>' +
                        '<td>' + resp.value[i].age + '</td>' +
                        '<td></td>' +
                        '<td></td>' +
                        '</tr>');
                }
            },
            error(xhr,status,error) {
            }
        });

    });

});