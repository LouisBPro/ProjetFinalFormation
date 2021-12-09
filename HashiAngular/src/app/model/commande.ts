import { Statut } from './statut';
export class Commande {
  constructor(
    private _id?: number | undefined,
    private _prixTotal?: number | undefined,
    private _statut?: Statut | undefined
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
}
