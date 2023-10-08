package HarapAlb.Entities;

import HarapAlb.Main.RefLinks;

import java.awt.*;

/*! \class Item
    \brief. Implementeaza notiunea abstracta de entitate activa din joc, "element cu care se poate interactiona: monstru, turn etc.".
 */

public abstract class Item {
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected RefLinks refLinks;        /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    /*! \fn public Item(RefLinks rfL, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei

        \param  reflink Referinte "shortcut" catre alte referinte
        \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
        \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
        \param  width   Latimea imaginii entitatii.
        \param  height  Inaltimea imaginii entitatii.
     */
    public Item(RefLinks rfl, float x, float y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.refLinks = rfl;

        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);
    /*! \fn public float GetX()
        \brief Returneaza coordonata pe axa X.
     */
    public float GetX() {
        return x;
    }

    /*! \fn public float GetY()
        \brief Returneaza coordonata pe axa Y.
     */
    public float GetY() {
        return y;
    }
}
