import { LigneCommandePk } from './ligne-commande-pk';
import { Plat } from './plat';
export class LigneCommande {
  private _id?: LigneCommandePk | undefined;
  private _quantite?: number | undefined;

  /**
   * Getter quantite
   * @return {number}
   */
  public get quantite(): number | undefined {
    return this._quantite;
  }

  /**
   * Setter quantite
   * @param {number} value
   */
  public set quantite(value: number | undefined) {
    this._quantite = value;
  }

  public get id(): LigneCommandePk | undefined {
    return this._id;
  }
  public set id(value: LigneCommandePk | undefined) {
    this._id = value;
  }
}
