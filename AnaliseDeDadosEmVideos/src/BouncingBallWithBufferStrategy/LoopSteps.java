/******************************************************************************
*
* COPYRIGHT Vin�cius G. Mendon�a ALL RIGHTS RESERVED.
*
* This software cannot be copied, stored, distributed without  
* Vin�cius G.Mendon�a prior authorization.
*
* This file was made available on http://www.pontov.com.br and it is free 
* to be restributed or used under Creative Commons license 2.5 br: 
* http://creativecommons.org/licenses/by-sa/2.5/br/
*
*******************************************************************************
* Este software nao pode ser copiado, armazenado, distribuido sem autoriza��o 
* a priori de Vin�cius G. Mendon�a
*
* Este arquivo foi disponibilizado no site http://www.pontov.com.br e est� 
* livre para distribui��o seguindo a licen�a Creative Commons 2.5 br: 
* http://creativecommons.org/licenses/by-sa/2.5/br/
*
******************************************************************************/
package BouncingBallWithBufferStrategy;

/**
 * This interface provide the methods executed in each step of a main loop. Each
 * step, the game process its logics and, if the frame rate are adequate, paint
 * it's screen.
 */
public interface LoopSteps
{
    /**
     * This event occurs before the first iteration of the loop, and only once.
     */
    void setup();

    /**
     * The processLogics method updates all game object states. The game will
     * process it's data model and define the next game situation that will be
     * painted on the screen.
     */
    void processLogics();

    /**
     * Renders the next actual game situation in a buffer, for future painting.
     */
    void renderGraphics();

    /**
     * Paints the rendered graphics in the screen.
     */
    void paintScreen();

    /**
     * Called when the game loop ended.
     */
    void tearDown();
}
