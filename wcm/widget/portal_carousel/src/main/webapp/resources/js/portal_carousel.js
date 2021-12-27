var portalCarousel = SuperWidget.extend({
    //variáveis da widget
    variavelNumerica: null,
    variavelCaracter: null,

    //método iniciado quando a widget é carregada
    init: function() {
    	this.iniciarCarousel();
    },
  
    //BIND de eventos
    bindings: {
        local: {
            'execute': ['click_executeAction']
        },
        global: {}
    },
 
    iniciarCarousel: function(){
    	var images = [
              {
                  src: '/portal_carousel/resources/images/img1.jpg',
                  alt: 'Imagem 1',
                  title: 'Imagem 1',
                  description: 'Descrição da imagem 1',
                  linktarget: '_blank',
                  linkhref: 'http://www.fluig.com',
                  linktext: 'fluig'
              },
              {
                  src: '/portal_carousel/resources/images/img2.jpg',
                  alt: 'Imagem 2',
                  title: 'Imagem 2',
                  description: 'Descrição da imagem 2',
                  linktarget: '_blank',
                  linkhref: 'http://www.fluig.com',
                  linktext: 'fluig'
              }
          ];
           
          var settings = {
              id: 'myFluigCarouselExample',
              images: images,
              indicators: true,
              startIndex: 0,
              interval: 5000,
              resize: true,
              autoSize: true
          };
           
          var carousel = FLUIGC.carousel('#carousel-example-generic_'+this.instanceId, settings);
    }

});

