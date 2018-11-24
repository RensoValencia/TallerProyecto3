/** 
 * @author Renso Valencia Ventura
 * @since : Sep 13, 2017
 * @version 1.0
 */
ValidacionCamposJS = {

    ALFANUMERICO: /^[a-zA-Z0-9\u00E1\u00E9\u00ED\u00F3\u00FA\u00F1\u00C1\u00C9\u00CD\u00D3\u00DA\u00D1\u00a1\u00bf\s!;,\*-:\.\\\/?#@()%"]+$/,
    KEYS_ESPECIALES: [13,9,27,8,37,38,39,40,41,61,63,161,45,46,36,35,33,34,95,112,113,114,115,116,117,118,119,120,121,122,123,191],
    LETRAS_DIERESIS: /^[a-zA-Z\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00c4\u00e4\u00cb\u00eb\u00cf\u00ef\u00d6\u00f6\u00dc\u00fc\u0027\s]+$/,
    SOLO_NUMEROS: /^[\d\.]+$/,
    SOLO_NUMEROS_ENTEROS: /^[\d]+$/,
    SERIE_VALIDAS: /^[bfFB\d\.]+$/,
    
    validaLetras: function (e){
        var key;

        if (e.which === null) {
            key = e.keyCode;
        } else if (e.which !== 0 && e.charCode !== 0) {
            key = e.which;
        } else {
            key = e.which;
            for(var i in this.KEYS_ESPECIALES) {
                if(key === this.KEYS_ESPECIALES[i]) {
                    return true;
                }				
                return false;
            }   
        }
        
        if( this.esFirefox() ) {
                if(e.ctrlKey) return true;
        }
        var tecla = String.fromCharCode(key).toLowerCase();
        return this.LETRAS_DIERESIS.test(tecla);
    },
    
    soloNumerosEnteros: function(e){
			
        var key;

        if (e.which === null) {
            key = e.keyCode;
        } else if (e.which !== 0 && e.charCode !== 0) {
            key = e.which;
        } else {
            key = e.which;
            for(var i in this.KEYS_ESPECIALES) {
                if(key === this.KEYS_ESPECIALES[i]) {
                    return true;
                }				
                return false;
            }   
        }
        
        if( this.esFirefox() ) {
                if(e.ctrlKey) return true;
        }
        var tecla = String.fromCharCode(key).toLowerCase();
        return this.SOLO_NUMEROS_ENTEROS.test(tecla);
    },
    
    soloNumeros: function(e){
			
        var key;

        if (e.which === null) {
            key = e.keyCode;
        } else if (e.which !== 0 && e.charCode !== 0) {
            key = e.which;
        } else {
            key = e.which;
            for(var i in this.KEYS_ESPECIALES) {
                if(key === this.KEYS_ESPECIALES[i]) {
                    return true;
                }				
                return false;
            }   
        }
        
        if( this.esFirefox() ) {
                if(e.ctrlKey) return true;
        }
        var tecla = String.fromCharCode(key).toLowerCase();
        return this.SOLO_NUMEROS.test(tecla);
    },
    
    soloSerie: function(e) {
        
        var key;

        if (e.which === null) {
            key = e.keyCode;
        } else if (e.which !== 0 && e.charCode !== 0) {
            key = e.which;
        } else {
            key = e.which;
            for(var i in this.KEYS_ESPECIALES) {
                if(key === this.KEYS_ESPECIALES[i]) {
                    return true;
                }				
                return false;
            }   
        }
        
        if( this.esFirefox() ) {
                if(e.ctrlKey) return true;
        }
        var tecla = String.fromCharCode(key).toLowerCase();
        return this.SERIE_VALIDAS.test(tecla);
        
    }, 
    
    soloNroYLetras: function(e) {
    
        var key;

        if (e.which === null) {
            key = e.keyCode;
        } else if (e.which !== 0 && e.charCode !== 0) {
            key = e.which;
        } else {
            key = e.which;
            for(var i in this.KEYS_ESPECIALES) {
                if(key === this.KEYS_ESPECIALES[i]) {
                    return true;
                }				
                return false;
            }   
        }
        
        if( this.esFirefox() ) {
                if(e.ctrlKey) return true;
        }
        var tecla = String.fromCharCode(key).toLowerCase();
        return this.ALFANUMERICO.test(tecla);
    }, 
    
    esFirefox: function() {
        if(navigator.userAgent.indexOf("Firefox") > -1) {
                return true;
        }
    },
    
    validarOnPaste: function(input, regex){
        setTimeout(function () {
            var valor = $("input[id='" + input +"']").val();
            if(!regex.test(valor)) {
                $("input[id='" + input +"']").val("");
            }
        }, 100);
    },
    
    validarOnPasteSoloNumeros: function(input) {
        this.validarOnPaste(input, this.SOLO_NUMEROS);
    },
    
    validarOnPasteSoloNumerosEnteros: function(input) {
        this.validarOnPaste(input, this.SOLO_NUMEROS_ENTEROS);
    },
    
    validarOnPasteSoloNroLetras: function(input) {
        this.validarOnPaste(input, this.ALFANUMERICO);
    }, 
    
    validarOnPasteSoloSerie: function(input) {
        this.validarOnPaste(input, this.SERIE_VALIDAS);
    }
};