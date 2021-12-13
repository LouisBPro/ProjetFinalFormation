import { Plat } from "./plat";
import { Restaurant } from "./restaurant";

export class LigneCartePk {
  constructor(private _plat?: Plat | undefined, private _restaurant?: Restaurant | undefined) {}
  /**
   * Getter plat
   * @return {Plat }
   */
  public get plat(): Plat | undefined {
    return this._plat;
  }
  /**
   * Setter plat
   * @param {Plat } value
   */
  public set plat(value: Plat | undefined) {
    this._plat = value;
  }

  public get restaurant(): Restaurant | undefined {
    return this._restaurant;
  }
  public set restaurant(value: Restaurant | undefined) {
    this._restaurant = value;
  }
}
