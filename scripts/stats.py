from matplotlib import pyplot as plt
import pandas as pd

import os
import json
import numpy as np
import glob


def get_data_files(dir_path="stats/"):
    list_of_files = filter(os.path.isfile,
                           glob.glob(dir_path + '*'))
    list_of_files = sorted(list_of_files,
                           key=os.path.getmtime)

    return list_of_files


def mean(l):
    return sum(l) / len(l)


def plot_win_stats(dir_path):
    files = get_data_files(dir_path)
    global_dic = {'params': []}

    for file in files:
        with open(file, 'r') as f:
            data = json.load(f)
        df = pd.DataFrame(data)
        print(file)

        dic = {}
        for i in range(len(df["game_1"])):
            if len(global_dic) < 3:
                global_dic[df["game_1"][i]["player_name"]] = {
                    'card_activated': [],
                    'card_drawn': [],
                    'card_invoked': [],
                    'card_points': [],
                    'cristal_gained_by_cristalization': [],
                    'crystals': [],
                    'final_card_ingame': [],
                    'final_invocation_gauge': [],
                    'is_winner': [],
                    'penalty_bonus_stats': [],
                    'penalty_card': [],
                    'prestige_points': []
                }

            dic[df["game_1"][i]["player_name"]] = {
                'card_activated': [],
                'card_drawn': [],
                'card_invoked': [],
                'card_points': [],
                'cristal_gained_by_cristalization': [],
                'crystals': [],
                'final_card_ingame': [],
                'final_invocation_gauge': [],
                'is_winner': [],
                'penalty_bonus_stats': [],
                'penalty_card': [],
                'prestige_points': []
            }

        for d in df:
            for player in df[d]:
                p_name = player["player_name"]
                dic[p_name]["card_activated"].append(player["card_activated"])
                dic[p_name]["card_drawn"].append(player["card_drawn"])
                dic[p_name]["card_invoked"].append(player["card_invoked"])
                dic[p_name]["card_points"].append(player["card_points"])
                dic[p_name]["cristal_gained_by_cristalization"].append(
                    player["cristal_gained_by_cristalization"])
                dic[p_name]["crystals"].append(player["crystals"])
                dic[p_name]["final_card_ingame"].append(
                    player["final_card_ingame"])
                dic[p_name]["final_invocation_gauge"].append(
                    player["final_invocation_gauge"])
                dic[p_name]["is_winner"].append(player["is_winner"])
                dic[p_name]["penalty_bonus_stats"].append(
                    player["penalty_bonus_stats"])
                dic[p_name]["penalty_card"].append(player["penalty_card"])
                dic[p_name]["prestige_points"].append(
                    player["prestige_points"])
        # print(dic)
        # df_dic = pd.DataFrame(dic)

        params = file.split("_")
        "MonteCarlo_1-1-1.json"
        branch, depth, action = params[1].split("-")
        global_dic["params"].append({
            "branch": int(branch),
            "depth": int(depth),
            "action": int(action[:-5])

        })

        for player_name, v in dic.items():
            if player_name != "params":
                global_dic[player_name]["card_activated"].append(
                    mean(v["card_activated"]))
                global_dic[player_name]["crystals"].append(mean(v["crystals"]))
                global_dic[player_name]["card_invoked"].append(
                    mean(v["card_invoked"]))
                global_dic[player_name]["card_drawn"].append(
                    mean(v["card_drawn"]))
                global_dic[player_name]["cristal_gained_by_cristalization"].append(
                    mean(v["cristal_gained_by_cristalization"]))
                global_dic[player_name]["final_card_ingame"].append(
                    mean(v["final_card_ingame"]))
                global_dic[player_name]["card_points"].append(
                    mean(v["card_points"]))
                global_dic[player_name]["final_invocation_gauge"].append(
                    mean(v["final_invocation_gauge"]))
                global_dic[player_name]["is_winner"].append(
                    sum(v["is_winner"]))
                global_dic[player_name]["penalty_card"].append(
                    mean(v["penalty_card"]))
                global_dic[player_name]["prestige_points"].append(
                    mean(v["prestige_points"]))
                global_dic[player_name]["penalty_bonus_stats"].append(
                    mean(v["penalty_bonus_stats"]))
                # global_dic[player_name]["params"].append([branch, depth, action[:-5]])

    generate_plot(global_dic)


def generate_plot(global_dic):
    offset = 60
    i = 0
    plt.style.use('ggplot')
    for player_name, v in global_dic.items():
        if player_name == "params":
            continue

        arr = np.array(v["is_winner"])
        largest = arr.argsort()[-3:][::-1]

        # for k, v2 in global_dic[player_name].items():
        plt.figure(1, figsize=(8, 3))
        for i, idx in enumerate(largest, start=1):
            plt.plot(global_dic[player_name]["is_winner"]
                     [idx-offset:idx+offset], label="is_winner", color="red")
            plt.plot(global_dic[player_name]["card_invoked"]
                     [idx-offset:idx+offset], label="card_invoked", color="yellow")
            plt.plot(global_dic[player_name]["card_drawn"]
                     [idx-offset:idx+offset], label="card_drawn", color="cyan")
            plt.plot(global_dic[player_name]["cristal_gained_by_cristalization"]
                     [idx-offset:idx+offset], label="cristal_gained_by_cristalization", color="magenta")
            plt.plot(global_dic[player_name]["crystals"]
                     [idx-offset:idx+offset], label="crystals", color="pink")
            plt.plot(global_dic[player_name]["final_card_ingame"]
                     [idx-offset:idx+offset], label="final_card_ingame")
            plt.plot(global_dic[player_name]["card_points"]
                     [idx-offset:idx+offset], label="card_points")
            plt.plot(global_dic[player_name]["final_invocation_gauge"]
                     [idx-offset:idx+offset], label="final_invocation_gauge")
            plt.plot(global_dic[player_name]["penalty_card"]
                     [idx-offset:idx+offset], label="penalty_card")
            plt.plot(global_dic[player_name]["prestige_points"]
                     [idx-offset:idx+offset], label="prestige_points")
            plt.xlabel(
                f"{global_dic['params'][idx]['branch']}-{global_dic['params'][idx]['depth']}-{global_dic['params'][idx]['action']} #(branch-depth-action)")
            plt.ylabel("Winrate %")
            plt.title(f"{player_name} #{i} {v['is_winner'][idx]:.2f}% winrate")
            # plt.legend(loc='upper center', bbox_to_anchor=(0.5, 1.05),
            #     ncol=3, fancybox=True, shadow=True)
            lg = plt.legend(bbox_to_anchor=(1.05, 1.0),
                            loc='upper left', prop={'size': 8})
            plt.tight_layout()

            # plt.savefig(f"scripts/{player_name}-{idx}-{i}.png")
            plt.savefig(f"scripts/{player_name}-{idx}-{i}.png",
                        dpi=150, bbox_extra_artists=(lg,), bbox_inches='tight')

            plt.clf()
            i += 1
    with open("scripts/global_dic.json", "w+") as fjson:
        fjson.write(json.dumps(global_dic))


def load_global_dic(path="scripts/global_dic.json"):
    with open(path, 'r') as f:
        return json.load(f)


if __name__ == "__main__":
    plot_win_stats("stats/")
#     generate_plot(load_global_dic())
