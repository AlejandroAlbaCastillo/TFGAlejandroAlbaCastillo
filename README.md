# Problemas y soluciones aplicadas

## Iconos TabPane
## Objetivo
!['TabPane Icons'](/ReadmeImages/tabPaneIcons.png)
### Problema
Scene Builder no deja añadir una imagen a TabPane

### Solución aplicada
Los iconos se han puesto en el TabPane poniendo el siguiente código

    <TabPane prefHeight="200.0" prefWidth="200.0" tabClosingPolicy="UNAVAILABLE" BorderPane.alignment="CENTER">
        <tabs>
          <Tab text="Sucursal">
              <graphic>
                  <ImageView>
                      <fitWidth>16</fitWidth>
                      <preserveRatio>true</preserveRatio>
                      <image>
                          <Image url="/Imagenes/Iconos/sucursal.png"/>
                      </image>
                  </ImageView>
              </graphic>
               <content>
               ...