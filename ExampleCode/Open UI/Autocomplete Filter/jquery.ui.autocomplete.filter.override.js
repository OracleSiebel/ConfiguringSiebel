$.extend($.ui.autocomplete, {
    filter: function(array, term) {
        var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex(term), "i" );
        return $.grep( array, function(value) {
            return matcher.test( value.label || value.value || value );
        });
    }
})