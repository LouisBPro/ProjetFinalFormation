import { LigneCartePk } from "./ligne-carte-pk";
import { Plat } from "./plat";

export class LigneCarte {
  constructor(
    private _id?: LigneCartePk | undefined,
    private _disponibilite?: Boolean | undefined
  ) {}
  /**
   * Getter id
   * @return {LigneCartePk }
   */
  public get id(): LigneCartePk | undefined {
    return this._id;
  }
  /**
   * Setter id
   * @param {LigneCartePk } value
   */
  public set id(value: LigneCartePk | undefined) {
    this._id = value;
  }
  // /**
  //  * Getter id
  //  * @return {Plat[]}
  //  */
  // public get plat(): Plat | undefined {
  //   return this._plat;
  // }
  // /**
  //  * Setter id
  //  * @param {Plat[]} value
  //  */
  // public set plat(value: Plat | undefined) {
  //   this._plat = value;
  // }
  /**
   * Getter disponibilite
   * @return {Boolean}
   */
  public get disponibilite(): Boolean | undefined {
    return this._disponibilite;
  }
  /**
   * Setter disponibilite
   * @param {Boolean} value
   */
  public set disponibilite(value: Boolean | undefined) {
    this._disponibilite = value;
  }
}
