import { LigneCommande } from './ligne-commande';
import { Statut } from './statut';
export class Commande {
  constructor(
    private _id?: number | undefined,
    private _prixTotal?: number | undefined,
    private _statut?: Statut | undefined,
    private _date?: Date | undefined,
    private _lignesCommande?: LigneCommande[] | undefined
  ) {}

  /**
   * Getter statut
   * @return {Statut}
   */
  public get statut(): Statut | undefined {
    return this._statut;
  }

  /**
   * Setter statut
   * @param {Statut} value
   */
  public set statut(value: Statut | undefined) {
    this._statut = value;
  }

  /**
   * Getter prixTotal
   * @return {number}
   */
  public get prixTotal(): number | undefined {
    return this._prixTotal;
  }

  /**
   * Setter prixTotal
   * @param {number} value
   */
  public set prixTotal(value: number | undefined) {
    this._prixTotal = value;
  }

  /**
   * Getter id
   * @return {number}
   */
  public get id(): number | undefined {
    return this._id;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set id(value: number | undefined) {
    this._id = value;
  }

  /**
   * Getter date
   * @return {Date}
   */
  public get date(): Date | undefined {
    return this._date;
  }

  /**
   * Setter date
   * @param {Date} value
   */
  public set date(value: Date | undefined) {
    this._date = value;
  }

  /**
   * Getter ligneCommande
   * @return {LigneCommande[]}
   */
  public get lignesCommande(): LigneCommande[] | undefined {
    return this._lignesCommande;
  }

  /**
   * Setter ligneCommande
   * @param {LigneCommande[]} value
   */
  public set lignesCommande(value: LigneCommande[] | undefined) {
    this._lignesCommande = value;
  }
}
